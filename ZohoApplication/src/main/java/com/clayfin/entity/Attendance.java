package com.clayfin.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Attendance{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer attendanceId;
	private LocalDate date;
	private LocalDateTime checkInTimestamp;
	private LocalDateTime CheckOutTimestamp;
	private Double spentHours;

	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;

}
