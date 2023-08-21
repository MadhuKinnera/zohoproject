package com.clayfin.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.clayfin.entity.Employee;
import com.clayfin.exception.EmployeeException;

public class JWTtokenImpl {

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	private static final String SECRET_KEY = "Hey,I am Zoho's Secret";

	public String generateJwtTokenByEmail(String email) {
		Employee user = null;
		try {
			user = employeeServiceImpl.getEmployeeByEmail(email);
			if (user == null) {
				return "Email not found";
			}
		} catch (EmployeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long expirationMillis = System.currentTimeMillis() + 3600000;

		String token = Jwts.builder().setSubject(user.getEmail()).setExpiration(new Date(expirationMillis))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();

		return token;
	}
}
