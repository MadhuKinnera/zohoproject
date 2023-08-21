package com.clayfin.controller;

import org.springframework.http.ResponseEntity;

import com.clayfin.dto.GeneralResponse;
import com.clayfin.entity.LeaveRecord;
import com.clayfin.exception.EmployeeException;
import com.clayfin.exception.LeaveException;

public class LeaveController {

	ResponseEntity<GeneralResponse> applyLeave(LeaveRecord leaveRecord, Integer employeeId)
			throws LeaveException, EmployeeException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> updateLeave(Integer leaveId, LeaveRecord leaveRecord) throws LeaveException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> deleteLeave(Integer leaveId) throws LeaveException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getLeaveByLeaveId(Integer leaveId) throws LeaveException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getLeavesByEmployeeId(Integer employeeId) throws EmployeeException, LeaveException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getAllLeavesByManagerId(Integer managerId)
			throws EmployeeException, LeaveException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getLeavesByEmployeeIdAndStatus(Integer employeeId, String status)
			throws LeaveException, EmployeeException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getLeavesByManagerIdAndStatus(Integer managerId, String status)
			throws LeaveException, EmployeeException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getLeavesByEmployeeIdAndLeaveType(Integer employeeId, String leaveType)
			throws LeaveException, EmployeeException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getLeavesByManagerIdAndLeaveType(Integer managerId, String leaveType)
			throws LeaveException, EmployeeException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

}
