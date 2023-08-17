package com.clayfin;



import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@ComponentScan
public class AzureProj1Application implements CommandLineRunner{
	@Autowired
    private DataSource dataSource;
    public static void main(String[] args) throws Exception {
        SpringApplication.run(AzureProj1Application.class, args);            // it wil start application
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Connection Polling datasource : "+ dataSource);  // check connection pooling
    }
	
	/*
	 * public static void main(String[] args) {
	 * SpringApplication.run(AzureProj1Application.class, args); }
	 */
}
