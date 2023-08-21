package com.clayfin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayfin.entity.Attendance;
import com.clayfin.entity.Employee;
import com.clayfin.entity.LeaveRecord;
import com.clayfin.entity.Task;
import com.clayfin.exception.AttendanceException;
import com.clayfin.exception.EmployeeException;
import com.clayfin.exception.LeaveException;
import com.clayfin.exception.TaskException;
import com.clayfin.repository.EmployeeRepo;
import com.clayfin.utility.Constants;
import com.clayfin.utility.RepoHelper;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private RepoHelper repoHelper;

	@Override
	public Employee addEmployee(Employee employee) throws EmployeeException {

		if (employee == null)
			throw new EmployeeException();

		return employeeRepo.save(employee);
	}

	@Override
	public Employee updateEmployee(Integer employeeId, Employee employee) throws EmployeeException {

		if (repoHelper.isEmployeeExist(employeeId))
			return employeeRepo.save(employee);
		else
			throw new EmployeeException(Constants.EMPLOYEE_NOT_FOUND_WITH_ID + employeeId);
	}

	@Override
	public Employee getEmployeeById(Integer employeeId) throws EmployeeException {
		return employeeRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeException(Constants.EMPLOYEE_NOT_FOUND_WITH_ID + employeeId));
	}

	@Override
	public Employee deleteEmployee(Integer employeeId) throws EmployeeException {

		Employee employee = getEmployeeById(employeeId);
		employeeRepo.delete(employee);

		return employee;
	}

	@Override
	public Employee getEmployeeByEmail(String email) throws EmployeeException {
		return employeeRepo.findByEmail(email)
				.orElseThrow(() -> new EmployeeException(Constants.EMPLOYEE_NOT_FOUND_WITH_EMAIL + email));

	}

	@Override
	public List<Employee> getAllEmployees() throws EmployeeException {
		List<Employee> employees = employeeRepo.findAll();

		if (employees.isEmpty())
			throw new EmployeeException(Constants.EMPLOYEES_NOT_FOUND);

		return employees;

	}

	@Override
	public Employee getEmployeeManager(Integer employeeId) throws EmployeeException {

		return employeeRepo.findByManagerEmployeeId(employeeId)
				.orElseThrow(() -> new EmployeeException(Constants.MANAGER_NOT_FOUND));

	}

	@Override
	public List<Employee> getEmployeesOfManager(Integer managerId) throws EmployeeException {

		Employee employee = employeeRepo.findById(managerId)
				.orElseThrow(() -> new EmployeeException(Constants.EMPLOYEE_NOT_FOUND_WITH_ID));

		if (employee.getSubEmployees().isEmpty())
			throw new EmployeeException(Constants.EMPLOYEES_NOT_FOUND);

		return employee.getSubEmployees();

	}

	@Override
	public List<Task> getAllTaskMyEmployeeId(Integer employeeId) throws EmployeeException, TaskException {
		
		Employee employee = getEmployeeById(employeeId);
	
		 if(employee.getTasks().isEmpty()) {
			 throw new TaskException(Constants.TASK_NOT_FOUND_WITH_EMPLOYEE_ID+employeeId);
		 }
		 
		 return employee.getTasks();
	}

	@Override
	public List<LeaveRecord> getAllLeavesByEmployeeId(Integer employeeId) throws EmployeeException, LeaveException {
		Employee employee = getEmployeeById(employeeId);
		
		 if(employee.getTasks().isEmpty()) {
			 throw new LeaveException(Constants.LEAVE_NOT_FOUND_WITH_EMPLOYEE_ID+employeeId);
		 }
		 
		 return employee.getLeaveRecords();
	}

	@Override
	public List<Attendance> getAllAttendanceByEmployeeId(Integer employeeId)
			throws EmployeeException, AttendanceException {
		Employee employee = getEmployeeById(employeeId);
		
		 if(employee.getTasks().isEmpty()) {
			 throw new AttendanceException(Constants.ATTENDANCE_NOT_FOUND_WITH_EMPLOYEE_ID+employeeId);
		 }
		 
		 return employee.getAttendances();

	}

}
