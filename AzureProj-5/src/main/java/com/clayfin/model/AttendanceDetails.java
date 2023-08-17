package com.clayfin.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AttendanceDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private int availableLeave;
	private int availablePermission;
	private Date date;
	private String loginTimestamp;
	private String logoutTime;
	private int totalHours;

	@ManyToOne
	private User user;

}
