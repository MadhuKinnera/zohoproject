package com.clayfin.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.clayfin.enums.RoleType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employee_id")
	private Integer employeeId;

	private String username;

	private String email;

	private String password;

	@ManyToOne
	private Employee manager;

	@OneToMany(mappedBy = "manager")
	private List<Employee> subEmployees;

	@Enumerated(EnumType.STRING)
	private RoleType role;

	@OneToMany(mappedBy = "employee")
	private List<Attendance> attendances;

	@OneToMany(mappedBy = "employee")
	private List<LeaveRecord> leaveRecords;

	@OneToMany(mappedBy = "employee")
	private List<Task> tasks;

}
