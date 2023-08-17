package com.clayfin.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clayfin.model.AttendanceDetails;

public interface AttendenceDetailsRepo extends CrudRepository<AttendanceDetails, Integer> {

	
}
