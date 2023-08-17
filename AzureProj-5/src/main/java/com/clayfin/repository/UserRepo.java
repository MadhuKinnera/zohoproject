package com.clayfin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clayfin.model.User;



public interface UserRepo extends JpaRepository<User,Integer> {

	User findByUsername(String username);

	User findByMail(String email);

	
	@Query("SELECT e FROM User e where e.managerId = :id")
	List<User> getAllEmployeesByManager(@Param("id") Integer id);
	
	
	
}
