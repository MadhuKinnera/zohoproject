package com.clayfin.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayfin.entity.Employee;
import com.clayfin.entity.Task;
import com.clayfin.enums.TaskStatus;
import com.clayfin.exception.EmployeeException;
import com.clayfin.exception.TaskException;
import com.clayfin.repository.EmployeeRepo;
import com.clayfin.repository.TaskRepo;
import com.clayfin.utility.Constants;
import com.clayfin.utility.RepoHelper;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepo taskRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private RepoHelper repoHelper;

	@Override
	public Task assingTask(Task task, Integer managerId, Integer employeeId) throws TaskException, EmployeeException {

		Employee employee = employeeRepo.findById(employeeId)
				.orElseThrow(() -> new EmployeeException(Constants.EMPLOYEE_NOT_FOUND_WITH_ID + employeeId));

		Employee manager = employeeRepo.findById(managerId)
				.orElseThrow(() -> new EmployeeException(Constants.EMPLOYEE_NOT_FOUND_WITH_ID + employeeId));
		
		if(!repoHelper.isValidEmployeeOfThisManager(managerId, employeeId))
			throw new TaskException(Constants.NOT_VALID_MANAGER);

		task.setAssignedTime(LocalDateTime.now());
		task.setEmployee(employee);
		task.setManager(manager);
		task.setStatus(TaskStatus.PENDING);

		return taskRepo.save(task);
	}

	@Override
	public Task updateTask(Integer taskId, Task task) throws TaskException {

		if (!repoHelper.isTaskExist(taskId))
			throw new TaskException(Constants.TASK_NOT_FOUND_WITH_ID + taskId);
		return taskRepo.save(task);
	}

	@Override
	public Task deleteTask(Integer taskId) throws TaskException {

		Task task = taskRepo.findById(taskId)
				.orElseThrow(() -> new TaskException(Constants.TASK_NOT_FOUND_WITH_ID + taskId));

		taskRepo.delete(task);

		return task;
	}

	@Override
	public Task getTaskById(Integer taskId) throws TaskException {
		Task task = taskRepo.findById(taskId)
				.orElseThrow(() -> new TaskException(Constants.TASK_NOT_FOUND_WITH_ID + taskId));

		return task;
	}

	@Override
	public List<Task> getAllTaskByEmployeeId(Integer employeeId) throws EmployeeException, TaskException {

		List<Task> tasks = taskRepo.findByEmployeeEmployeeId(employeeId);

		if (tasks.isEmpty())
			throw new TaskException(Constants.TASK_NOT_FOUND_WITH_EMPLOYEE_ID + employeeId);

		return tasks;
	}

	@Override
	public List<Task> getAllTaskAssignedByManagerId(Integer managerId) throws EmployeeException, TaskException {
		List<Task> tasks = taskRepo.findByManagerEmployeeId(managerId);

		if (tasks.isEmpty())
			throw new TaskException(Constants.TASK_NOT_FOUND_WITH_EMPLOYEE_ID + managerId);

		return tasks;
	}

	@Override
	public List<Task> getAllTaskByStatusAndEmployeeId(Integer employeeId, String status)
			throws EmployeeException, TaskException {
		List<Task> tasks = taskRepo.findByEmployeeEmployeeIdAndStatus(employeeId,status);

		if (tasks.isEmpty())
			throw new TaskException(Constants.TASK_NOT_FOUND_WITH_EMPLOYEE_ID + employeeId);

		return tasks;
	}

	@Override
	public List<Task> getAllTaskByDateAndEmployeeId(Integer employeeId, LocalDate date)
			throws EmployeeException, TaskException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> getAllTaskByStatusAndManagerId(Integer managerId, String status)
			throws EmployeeException, TaskException {
		List<Task> tasks = taskRepo.findByManagerEmployeeIdAndStatus(managerId,status);

		if (tasks.isEmpty())
			throw new TaskException(Constants.TASK_NOT_FOUND_WITH_EMPLOYEE_ID + managerId);

		return tasks;
	}

	@Override
	public List<Task> getAllTaskByDateAndManagerId(Integer managerId, LocalDate date)
			throws EmployeeException, TaskException {
		// TODO Auto-generated method stub
		return null;
	}

}
