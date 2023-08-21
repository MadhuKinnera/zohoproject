package com.clayfin.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.clayfin.entity.Attendance;
import com.clayfin.entity.Employee;
import com.clayfin.exception.AttendanceException;
import com.clayfin.exception.EmployeeException;
import com.clayfin.repository.AttendenceRepo;
import com.clayfin.repository.EmployeeRepo;
import com.clayfin.utility.Constants;
import com.clayfin.utility.RepoHelper;

public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendenceRepo attendanceRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private RepoHelper repoHelper;

	@Override
	public List<Attendance> getAttendanceByDateAndEmployeeId(LocalDate date, Integer employeeId)
			throws EmployeeException, AttendanceException {

		List<Attendance> attendances = attendanceRepo.findByEmployeeEmployeeIdAndDate(employeeId, date);

		if (attendances.isEmpty())
			throw new AttendanceException(
					Constants.ATTENDANCE_NOT_FOUND_WITH_EMPLOYEE_ID + employeeId + Constants.WITH_DATE + date);

		return attendances;

	}

	@Override
	public List<Attendance> getAttendanceByEmployeeId(Integer employeeId)
			throws AttendanceException, EmployeeException {
		List<Attendance> attendances = attendanceRepo.findByEmployeeEmployeeId(employeeId);

		if (attendances.isEmpty())
			throw new AttendanceException(Constants.ATTENDANCE_NOT_FOUND_WITH_ID + employeeId);

		return attendances;
	}

	@Override
	public Attendance updateAttendance(Integer attendanceId, Attendance attendance) throws AttendanceException {

		if (!isAttendanceExist(attendanceId))
			throw new AttendanceException(Constants.ATTENDANCE_NOT_FOUND_WITH_ATTENDANCE_ID + attendanceId);
		return attendanceRepo.save(attendance);

	}

	@Override
	public Attendance deleteAttendance(Integer attendanceId) throws AttendanceException {
		Attendance attendance = attendanceRepo.findById(attendanceId).orElseThrow(
				() -> new AttendanceException(Constants.ATTENDANCE_NOT_FOUND_WITH_ATTENDANCE_ID + attendanceId));
		attendanceRepo.delete(attendance);

		return attendance;
	}

	@Override
	public Attendance regularize(Integer employeeId, LocalDate date, LocalDateTime fromTime, LocalDateTime toTime)
			throws AttendanceException, EmployeeException {

		Integer hours = toTime.getHour() - fromTime.getHour();

		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeException(Constants.EMPLOYEE_NOT_FOUND_WITH_ID + employeeId));

		if (hours > 2)
			throw new AttendanceException(Constants.NOT_REGURALIZABLE);

		List<Attendance> alreadyPresentAttendance = getAttendanceByDateAndEmployeeId(date, employeeId);

		for (int i = 0; i < alreadyPresentAttendance.size() - 1; i++) {
			if (alreadyPresentAttendance.get(i).getCheckOutTimestamp().isBefore(fromTime)) {
				if (i != alreadyPresentAttendance.size() - 1) {
					Attendance attendance = new Attendance();
					attendance.setCheckInTimestamp(fromTime);
					attendance.setCheckOutTimestamp(toTime);
					attendance.setDate(date);
					attendance.setEmployee(employee);
					attendance.setSpentHours(hours.doubleValue());
					return attendance;
				} else {
					Attendance nextAttendance = alreadyPresentAttendance.get(i + 1);
					if (nextAttendance.getCheckInTimestamp().isAfter(toTime)) {

						Attendance attendance = new Attendance();
						attendance.setCheckInTimestamp(fromTime);
						attendance.setCheckOutTimestamp(toTime);
						attendance.setDate(date);
						attendance.setEmployee(employee);
						attendance.setSpentHours(hours.doubleValue());
						return attendance;

					} else {
						throw new AttendanceException("Attendance Time is Overlapping");
					}
				}
			}
		}

		throw new AttendanceException(Constants.ATTENDANCE_NOT_FOUND);

	}

	@Override
	public Boolean isAttendanceExist(Integer attendanceId) throws AttendanceException {
		return attendanceRepo.findById(attendanceId).isPresent();

	}

	@Override
	public Attendance checkInAttendance(Integer employeeId) throws EmployeeException, AttendanceException {

		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeException(Constants.EMPLOYEE_NOT_FOUND_WITH_ID + employeeId));

		Attendance lastAttendance = attendanceRepo.findTopByEmployeeEmployeeIdOrderByEmployeeEmployeeIdDesc(employeeId);

		Attendance attendance = new Attendance();

		if (lastAttendance == null) {

			attendance.setCheckInTimestamp(LocalDateTime.now());
			attendance.setDate(LocalDate.now());
			attendance.setEmployee(employee);
			return attendanceRepo.save(attendance);

		}

		if (lastAttendance.getCheckOutTimestamp() == null)
			throw new AttendanceException("Need to CheckOut before CheckIn ");

		attendance.setCheckInTimestamp(LocalDateTime.now());
		attendance.setDate(LocalDate.now());
		attendance.setEmployee(employee);
		return attendanceRepo.save(attendance);

	}

	@Override
	public Attendance checkOutAttendance(Integer employeeId) throws AttendanceException, EmployeeException {
		
		if(!repoHelper.isEmployeeExist(employeeId))
			throw new EmployeeException(Constants.EMPLOYEE_NOT_FOUND_WITH_ID+employeeId);

		Attendance lastAttendance = attendanceRepo.findTopByEmployeeEmployeeIdOrderByEmployeeEmployeeIdDesc(employeeId);

		if (lastAttendance != null && lastAttendance.getCheckOutTimestamp() != null) {
			lastAttendance.setCheckOutTimestamp(LocalDateTime.now());
		}

		 throw new AttendanceException("You Have To Check In First To CheckOut");
	}

	


}
