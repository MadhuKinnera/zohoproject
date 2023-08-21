package com.clayfin.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clayfin.entity.LeaveRecord;

public interface LeaveRepo extends JpaRepository<LeaveRecord, Integer> {

	List<LeaveRecord> findByEmployeeEmployeeId(Integer employeeId);

	List<LeaveRecord> findByEmployeeEmployeeIdAndCreatedTimestamp(Integer employeeId, LocalDate timestamp);

	List<LeaveRecord> findByEmployeeEmployeeIdAndStatus(Integer employeeId, String status);

	List<LeaveRecord> findByEmployeeEmployeeIdAndLeaveType(Integer employeeId, String leaveType);

	List<LeaveRecord> findByEmployeeManagerEmployeeId(Integer managerId);

	List<LeaveRecord> findByEmployeeManagerEmployeeIdAndStatus(Integer managerId, String status);

	List<LeaveRecord> findByEmployeeManagerEmployeeIdAndLeaveType(Integer managerId, String leaveType);

}