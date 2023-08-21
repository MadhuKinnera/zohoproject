package com.clayfin.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.clayfin.entity.Task;

public interface TaskRepo extends CrudRepository<Task, Integer> {

	List<Task> findByEmployeeEmployeeId(Integer employeeId);
	
	
	List<Task> findByManagerEmployeeId(Integer managerId);
	
	List<Task> findByEmployeeEmployeeIdAndStatus(Integer employeeId,String status);

	List<Task> findByManagerEmployeeIdAndStatus(Integer managerId,String status);
	
	//native query.
	//List<Task> getAllTaskByEmployeeIdAndDate(Integer employeeId,LocalDateTime startDate,LocalDateTime endDate);
}
