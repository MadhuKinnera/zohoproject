package com.clayfin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.clayfin.model.Leavetable;

public interface LeaveRepo extends JpaRepository<Leavetable, Integer>{

	
	
	
	List<Leavetable> findAllByStatus(String status);
	
	/*
	@Query("select a from leavetable a where a.status = :id")
	public List<Leavetable> getAllPendingLeaves(@Param("id") String Status);
	

	
	

	@Query("SELECT e FROM leavetable e where e.status='emergency'")
	List<Registration> findAllByEmgLeave();

*/
}
