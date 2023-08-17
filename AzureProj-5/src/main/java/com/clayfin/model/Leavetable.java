package com.clayfin.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Leavetable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	private int days;
	private Timestamp timestamp;
	private String status;
	private String reason;
	@ManyToOne
	private User user;

}
