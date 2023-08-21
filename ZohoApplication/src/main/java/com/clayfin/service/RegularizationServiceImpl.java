package com.clayfin.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayfin.dto.RegularizeDTO;
import com.clayfin.entity.Attendance;
import com.clayfin.entity.Employee;
import com.clayfin.entity.RegularizationRequest;
import com.clayfin.enums.RegularizationStatus;
import com.clayfin.exception.AttendanceException;
import com.clayfin.exception.EmployeeException;
import com.clayfin.exception.RegularizationException;
import com.clayfin.repository.AttendenceRepo;
import com.clayfin.repository.EmployeeRepo;
import com.clayfin.repository.RegularizationRepo;
import com.clayfin.utility.Constants;
import com.clayfin.utility.RepoHelper;

@Service
public class RegularizationServiceImpl implements RegularizationService {

	@Autowired
	private RegularizationRepo regularizationRepo;

	@Autowired
	private AttendenceRepo attendanceRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private RepoHelper repoHelper;

	@Autowired
	private AttendanceService attendanceService;

	@Override
	public RegularizationRequest addRegularizationRequest(RegularizeDTO request, Integer employeeId)
			throws AttendanceException, EmployeeException {

		var regularizeRequest = new RegularizationRequest();

		regularizeRequest.setCreatedTimestamp(LocalDateTime.now());
		regularizeRequest.setStatus(RegularizationStatus.PENDING);

		LocalDateTime fromTime = request.getFromTime();
		LocalDateTime toTime = request.getToTime();

		LocalDate date = request.getDate();

		LocalTime spentHours = repoHelper.findTimeBetweenTimestamps(fromTime, toTime);

		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeException(Constants.EMPLOYEE_NOT_FOUND_WITH_ID + employeeId));

		regularizeRequest.setEmployee(employee);

		if (spentHours.getHour() > 2)
			throw new AttendanceException(Constants.NOT_REGURALIZABLE);

		List<Attendance> alreadyPresentAttendance = attendanceService.getAttendanceByDateAndEmployeeId(date,
				employeeId);

		System.out.println(alreadyPresentAttendance.size() + "  attendances Found ");

		for (int i = 0; i < alreadyPresentAttendance.size() - 1; i++) {
			if (alreadyPresentAttendance.get(i).getCheckOutTimestamp().isBefore(fromTime)) {
				if (i != alreadyPresentAttendance.size() - 1) {
					Attendance attendance = new Attendance();
					attendance.setCheckInTimestamp(fromTime);
					attendance.setCheckOutTimestamp(toTime);
					attendance.setDate(date);
					attendance.setEmployee(employee);
					LocalTime spentTime = repoHelper.findTimeBetweenTimestamps(attendance.getCheckInTimestamp(),
							attendance.getCheckOutTimestamp());

					attendance.setSpentHours(spentTime);
					return regularizationRepo.save(regularizeRequest);

				} else {
					Attendance nextAttendance = alreadyPresentAttendance.get(i + 1);
					if (nextAttendance.getCheckInTimestamp().isAfter(toTime)) {

						Attendance attendance = new Attendance();
						attendance.setCheckInTimestamp(fromTime);
						attendance.setCheckOutTimestamp(toTime);
						attendance.setDate(date);
						attendance.setEmployee(employee);
						LocalTime spentTime = repoHelper.findTimeBetweenTimestamps(attendance.getCheckInTimestamp(),
								attendance.getCheckOutTimestamp());

						attendance.setSpentHours(spentTime);

						return regularizationRepo.save(regularizeRequest);

					} else {
						throw new AttendanceException("Attendance Time is Overlapping");
					}
				}
			}
		}

		if (alreadyPresentAttendance.isEmpty())
			throw new AttendanceException(Constants.ATTENDANCE_NOT_FOUND);
		else
			throw new AttendanceException("Cannot Reguralize the for Given Timings");

	}

	@Override
	public RegularizationRequest getRegularizationRequest(Integer regularizationReqeustId)
			throws RegularizationException {

		return regularizationRepo.findById(regularizationReqeustId).orElseThrow(() -> new RegularizationException(
				"Regularization Request Not Found with Regularization Id " + regularizationReqeustId));

	}

	@Override
	public RegularizationRequest updateRegularizationStatus(Integer regularizationId, RegularizationStatus status)
			throws RegularizationException {

		RegularizationRequest request = regularizationRepo.findById(regularizationId)
				.orElseThrow(() -> new RegularizationException(
						"Regularization Request Not Found with Regularization Id " + regularizationId));

		if (request.getStatus() != RegularizationStatus.PENDING)
			throw new RegularizationException("Regularization Request Status Already In " + status);

		request.setStatus(status);

		Attendance attendance = new Attendance();
		attendance.setCheckInTimestamp(request.getCheckInTimestamp());
		attendance.setCheckOutTimestamp(request.getCheckOutTimestamp());
		attendance.setDate(request.getDate());
		attendance.setEmployee(request.getEmployee());

		LocalTime spentTime = repoHelper.findTimeBetweenTimestamps(request.getCheckInTimestamp(),
				request.getCheckOutTimestamp());

		attendance.setSpentHours(spentTime);

		attendanceRepo.save(attendance);

		return regularizationRepo.save(request);
	}

	@Override
	public List<RegularizationRequest> getAllRegularizationRequestByEmployeeId(Integer employeeId)
			throws RegularizationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RegularizationRequest> getAllRegularizationRequestByManagerId(Integer managerId)
			throws RegularizationException {
		// TODO Auto-generated method stub
		return null;
	}

}
