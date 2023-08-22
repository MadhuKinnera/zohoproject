package com.clayfin.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayfin.dto.RegularizeDTO;
import com.clayfin.entity.Attendance;
import com.clayfin.entity.RegularizationRequest;
import com.clayfin.enums.RegularizationStatus;
import com.clayfin.exception.AttendanceException;
import com.clayfin.exception.EmployeeException;
import com.clayfin.exception.RegularizationException;
import com.clayfin.repository.AttendenceRepo;
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
	private RepoHelper repoHelper;

	@Override
	public RegularizationRequest addRegularizationRequest(RegularizeDTO request, Integer employeeId)
			throws AttendanceException, EmployeeException, RegularizationException {

		Boolean isValid = repoHelper.isValidRegularizationRequest(request, employeeId);

		if (!isValid)
			throw new RegularizationException("Not A valid Regularization Request ");

		if (request.getFromTime().getDayOfYear() != request.getToTime().getDayOfYear()
				|| request.getDate().getDayOfYear() != request.getToTime().getDayOfYear())
			throw new RegularizationException("Invalid Dates Provided ");

		var regularizeRequest = new RegularizationRequest();

		regularizeRequest.setCreatedTimestamp(LocalDateTime.now());
		regularizeRequest.setStatus(RegularizationStatus.PENDING);
		regularizeRequest.setCheckInTimestamp(request.getFromTime());
		regularizeRequest.setCheckOutTimestamp(request.getToTime());
		regularizeRequest.setDate(request.getDate());

		return regularizationRepo.save(regularizeRequest);
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

		if (status.equals(RegularizationStatus.ACCEPTED)) {

			Attendance attendance = new Attendance();
			attendance.setCheckInTimestamp(request.getCheckInTimestamp());
			attendance.setCheckOutTimestamp(request.getCheckOutTimestamp());
			attendance.setDate(request.getDate());
			attendance.setEmployee(request.getEmployee());

			LocalTime spentTime = repoHelper.findTimeBetweenTimestamps(request.getCheckInTimestamp(),
					request.getCheckOutTimestamp());

			attendance.setSpentHours(spentTime);

			attendanceRepo.save(attendance);

		}

		return regularizationRepo.save(request);
	}

	@Override
	public List<RegularizationRequest> getAllRegularizationRequestByEmployeeId(Integer employeeId)
			throws RegularizationException {

		List<RegularizationRequest> requests = regularizationRepo.findByEmployeeEmployeeId(employeeId);

		if (requests.isEmpty())
			throw new RegularizationException(Constants.REGULARIZATION_REQUEST_NOT_FOUND);

		return requests;
	}

	@Override
	public List<RegularizationRequest> getAllRegularizationRequestByManagerId(Integer managerId)
			throws RegularizationException {

		List<RegularizationRequest> requests = regularizationRepo.findByEmployeeManagerEmployeeId(managerId);

		if (requests.isEmpty())
			throw new RegularizationException(Constants.REGULARIZATION_REQUEST_NOT_FOUND);

		return requests;

	}

	@Override
	public List<RegularizationRequest> getAllRegularizationRequestByEmployeeIdAndStatus(Integer employeeId,
			RegularizationStatus status) throws RegularizationException {

		List<RegularizationRequest> requests = regularizationRepo.findByEmployeeEmployeeIdAndStatus(employeeId, status);

		if (requests.isEmpty())
			throw new RegularizationException(Constants.REGULARIZATION_REQUEST_NOT_FOUND);

		return requests;

	}

	@Override
	public List<RegularizationRequest> getAllRegularizationRequestByManagerIdAndStatus(Integer managerId,
			RegularizationStatus status) throws RegularizationException {
		List<RegularizationRequest> requests = regularizationRepo.findByEmployeeManagerEmployeeIdAndStatus(managerId, status);

		if (requests.isEmpty())
			throw new RegularizationException(Constants.REGULARIZATION_REQUEST_NOT_FOUND);

		return requests;
	}

}
