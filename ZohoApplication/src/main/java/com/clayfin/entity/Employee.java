package com.clayfin.entity;

import java.util.List;

import com.clayfin.enums.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
