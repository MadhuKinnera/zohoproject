package com.clayfin.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.clayfin.enums.TaskStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer taskId;
	private String taskDescription;
	private LocalDateTime assignedTime;

	@Enumerated(EnumType.STRING)
	private TaskStatus status;
	
	@ManyToOne
	@JoinColumn(name="assinged_employee")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="assinging_manager")
	private Employee manager;
	

}
