package com.clayfin.service;

import java.util.List;

import com.clayfin.dto.RegularizeDTO;
import com.clayfin.entity.RegularizationRequest;
import com.clayfin.enums.RegularizationStatus;
import com.clayfin.exception.AttendanceException;
import com.clayfin.exception.EmployeeException;
import com.clayfin.exception.RegularizationException;


public interface RegularizationService {
	
	RegularizationRequest addRegularizationRequest(RegularizeDTO request,Integer employeeId) throws AttendanceException,EmployeeException;
	
	RegularizationRequest getRegularizationRequest(Integer regularizationReqeustId) throws RegularizationException;
	
	RegularizationRequest updateRegularizationStatus(Integer regularizationId,RegularizationStatus status) throws RegularizationException;
	
	List<RegularizationRequest> getAllRegularizationRequestByEmployeeId(Integer employeeId)throws RegularizationException;
	
	List<RegularizationRequest> getAllRegularizationRequestByManagerId(Integer managerId) throws RegularizationException;
	
	
}
