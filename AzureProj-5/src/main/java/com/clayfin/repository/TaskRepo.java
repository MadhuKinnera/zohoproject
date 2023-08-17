package com.clayfin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clayfin.model.Task;

public interface TaskRepo extends CrudRepository<Task, Integer> {
	@Query("SELECT e FROM Task e where e.user.id = :id")
	List<Task> findAllByUser_Id(Integer id);

	// List<Task> getAllEmployeesByManager(@Param("id") Integer id);
}
