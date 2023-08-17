package com.clayfin.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "check_in_and_out")
public class DataEntity {
	@Id
	@Column(name = "id1")
	private String id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "checkin_date")
	private LocalDate checkinDate;

	@Column(name = "checkout_date")
	private LocalDate checkoutDate;

	@Column(name = "checkin_time")
	private LocalTime checkinTime;

	@Column(name = "checkout_time")
	private LocalTime checkoutIme;

}
