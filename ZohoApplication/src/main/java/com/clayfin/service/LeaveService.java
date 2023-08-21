package com.clayfin.service;

import java.util.List;

import com.clayfin.entity.LeaveRecord;
import com.clayfin.exception.EmployeeException;
import com.clayfin.exception.LeaveException;

public interface LeaveService {

	LeaveRecord applyLeave(LeaveRecord leaveRecord, Integer employeeId) throws LeaveException, EmployeeException;

	LeaveRecord updateLeave(Integer leaveId, LeaveRecord leaveRecord) throws LeaveException;

	LeaveRecord deleteLeave(Integer leaveId) throws LeaveException;

	LeaveRecord getLeaveByLeaveId(Integer leaveId) throws LeaveException;

	List<LeaveRecord> getLeavesByEmployeeId(Integer employeeId) throws EmployeeException, LeaveException;

	List<LeaveRecord> getAllLeavesByManagerId(Integer managerId) throws EmployeeException, LeaveException;

	List<LeaveRecord> getLeavesByEmployeeIdAndStatus(Integer employeeId, String status)
			throws LeaveException, EmployeeException;

	List<LeaveRecord> getLeavesByManagerIdAndStatus(Integer managerId, String status)
			throws LeaveException, EmployeeException;

	List<LeaveRecord> getLeavesByEmployeeIdAndLeaveType(Integer employeeId, String leaveType)
			throws LeaveException, EmployeeException;

	List<LeaveRecord> getLeavesByManagerIdAndLeaveType(Integer managerId, String leaveType)
			throws LeaveException, EmployeeException;

}
