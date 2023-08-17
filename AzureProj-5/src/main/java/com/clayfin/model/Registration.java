package com.clayfin.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Registration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int registerId;

	@OneToOne
	private User user;

	private Date registerTime;

	private Date AuthorisedTIme;

	private String status;

	private String role;

}
