package com.clayfin.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	@Column(unique = true)
	private String username;
	@Column(unique = true)
	private String mail;
	private String password;

	private Integer managerId;
	private String role;

	@OneToMany(mappedBy = "user")
	private List<AttendanceDetails> attendanceDetails;

	@OneToMany(mappedBy = "user")
	private List<Leavetable> leaveTables;

	@OneToMany(mappedBy = "user")
	private List<Task> tasks;

}
