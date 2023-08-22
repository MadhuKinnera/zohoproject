package com.clayfin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clayfin.dto.GeneralResponse;
import com.clayfin.dto.RegularizeDTO;
import com.clayfin.enums.RegularizationStatus;
import com.clayfin.exception.AttendanceException;
import com.clayfin.exception.EmployeeException;
import com.clayfin.exception.RegularizationException;
import com.clayfin.service.RegularizationService;

@RestController
@RequestMapping("/regularization")
public class RegularizationController {

	@Autowired
	private RegularizationService regularizationService;

	@PostMapping("/addRegularization/{employeeId}")
	ResponseEntity<GeneralResponse> addRegularizationRequest(@RequestBody RegularizeDTO request,
			@PathVariable Integer employeeId) throws RegularizationException, AttendanceException, EmployeeException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Regularization Request Added ");
		generalResponse.setData(regularizationService.addRegularizationRequest(request, employeeId));

		return ResponseEntity.ok(generalResponse);

	}

	@GetMapping("/getRegularization/{regularizationReqeustId}")
	ResponseEntity<GeneralResponse> getRegularizationRequest(@PathVariable Integer regularizationReqeustId)
			throws RegularizationException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Regularization Request Found with Regularization Id " + regularizationReqeustId);
		generalResponse.setData(regularizationService.getRegularizationRequest(regularizationReqeustId));

		return ResponseEntity.ok(generalResponse);
	}

	@PutMapping("/updateRegularizationStatus/{regularizationId}/{status}/{managerId}")
	ResponseEntity<GeneralResponse> updateRegularizationStatus(@PathVariable Integer regularizationId,
			@PathVariable RegularizationStatus status,@PathVariable Integer managerId) throws RegularizationException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Regularization Request Status updated to " + status);
		generalResponse.setData(regularizationService.updateRegularizationStatusAndManagerId(regularizationId, status,managerId));

		return ResponseEntity.ok(generalResponse);

	}

	@GetMapping("/getRegularizationRequestsByEmployee/{employeeId}")
	ResponseEntity<GeneralResponse> getRegularizationRequestByEmployeeId(@PathVariable Integer employeeId)
			throws RegularizationException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Regularization Requests Found By Employee Id  " + employeeId);
		generalResponse.setData(regularizationService.getRegularizationRequestByEmployeeId(employeeId));

		return ResponseEntity.ok(generalResponse);

	}
	
	@DeleteMapping("/deleteRegularizationRequestsByRegularizationId/{regularizationId}")
	ResponseEntity<GeneralResponse> deleteRegularizationRequestByEmployeeId(@PathVariable Integer regularizationId)
			throws RegularizationException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Regularization Requests Deleted By Regularization Id  " + regularizationId);
		generalResponse.setData(regularizationService.deleteRegularizationById(regularizationId));

		return ResponseEntity.ok(generalResponse);

	}

	@GetMapping("/getRegularizationRequestsByManager/{managerId}")
	ResponseEntity<GeneralResponse> getRegularizationRequestByManagerId(@PathVariable Integer managerId)
			throws RegularizationException {

		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Regularization Requests Found By Manager Id " + managerId);
		generalResponse.setData(regularizationService.getRegularizationRequestByManagerId(managerId));

		return ResponseEntity.ok(generalResponse);
	}

	@GetMapping("/getRegularizationRequestsByEmployeeAndStatus/{employeeId}/{status}")
	ResponseEntity<GeneralResponse> getRegularizationRequestByEmployeeIdAndStatus(@PathVariable Integer employeeId,
			@PathVariable RegularizationStatus status) throws RegularizationException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Regularization Requests Found By EmployeeId And Status ");
		generalResponse
				.setData(regularizationService.getRegularizationRequestByEmployeeIdAndStatus(employeeId, status));

		return ResponseEntity.ok(generalResponse);

	}

	@GetMapping("/getRegularizationRequestsByManagerAndStatus/{managerId}/{status}")
	ResponseEntity<GeneralResponse> getRegularizationRequestByManagerIdAndStatus(@PathVariable Integer managerId,
			@PathVariable RegularizationStatus status) throws RegularizationException {
		var generalResponse = new GeneralResponse();

		generalResponse.setMessage("Regularization Requests Found By Manager Id And Status ");
		generalResponse.setData(regularizationService.getRegularizationRequestByManagerIdAndStatus(managerId, status));

		return ResponseEntity.ok(generalResponse);

	}

}
