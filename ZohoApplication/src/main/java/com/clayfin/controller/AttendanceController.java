package com.clayfin.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.clayfin.dto.GeneralResponse;
import com.clayfin.entity.Attendance;
import com.clayfin.exception.AttendanceException;
import com.clayfin.exception.EmployeeException;

@RestController
public class AttendanceController {

	ResponseEntity<GeneralResponse> checkInAttendance(Integer employeeId)
			throws EmployeeException, AttendanceException {

		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> checkOutAttendance(Integer employeeId)
			throws EmployeeException, AttendanceException {

		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getAttendanceByDateAndEmployeeId(LocalDate date, Integer employeeId)
			throws EmployeeException, AttendanceException {

		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> getAttendanceByEmployeeId(Integer employeeId)
			throws AttendanceException, EmployeeException {

		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> updateAttendance(Integer attendanceId, Attendance attendance)
			throws AttendanceException {

		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> deleteAttendance(Integer attendanceId) throws AttendanceException {

		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

	ResponseEntity<GeneralResponse> regularize(Integer employeeId, LocalDate date, LocalDateTime fromTime,
			LocalDateTime toTime) throws AttendanceException, EmployeeException {

		var generalResponse = new GeneralResponse();

		return ResponseEntity.ok(generalResponse);
	}

}
