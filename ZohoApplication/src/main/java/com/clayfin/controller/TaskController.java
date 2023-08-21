package com.clayfin.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;

import com.clayfin.dto.GeneralResponse;
import com.clayfin.entity.Task;
import com.clayfin.exception.EmployeeException;
import com.clayfin.exception.TaskException;

public class TaskController {

	ResponseEntity<GeneralResponse> assingTask(Task task, Integer managerId, Integer employeeId)
			throws TaskException, EmployeeException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> updateTask(Integer taskId, Task task) throws TaskException, EmployeeException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> deleteTask(Integer taskId) throws TaskException, EmployeeException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getTaskById(Integer taskId) throws TaskException, EmployeeException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getAllTaskByEmployeeId(Integer employeeId) throws EmployeeException, TaskException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getAllTaskAssignedByManagerId(Integer managerId)
			throws EmployeeException, TaskException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getAllTaskByStatusAndEmployeeId(Integer employeeId, String status)
			throws EmployeeException, TaskException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getAllTaskByDateAndEmployeeId(Integer employeeId, LocalDate date)
			throws EmployeeException, TaskException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getAllTaskByStatusAndManagerId(Integer managerId, String status)
			throws EmployeeException, TaskException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getAllTaskByDateAndManagerId(Integer managerId, LocalDate date)
			throws EmployeeException, TaskException {
		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

}
