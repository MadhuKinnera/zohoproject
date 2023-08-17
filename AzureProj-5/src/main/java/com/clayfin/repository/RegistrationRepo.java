package com.clayfin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.clayfin.model.Registration;

public interface RegistrationRepo extends JpaRepository<Registration, Integer> {

	@Query("SELECT e FROM Registration e where e.status='pending'")
	List<Registration> findAllByAutho();
}
