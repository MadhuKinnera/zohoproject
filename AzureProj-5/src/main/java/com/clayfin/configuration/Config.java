
package com.clayfin.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

@SuppressWarnings("deprecation")

@EnableWebSecurity

@CrossOrigin("*")
@Configuration
public class Config extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// System.out.println(auth.getName());

		 http.csrf()
	        .disable()
	        .authorizeRequests().antMatchers("/home").permitAll().and()
	        .authorizeRequests().antMatchers("/signup").permitAll().and()
	        .authorizeRequests().antMatchers("/employeeee").permitAll().and()
	        .authorizeRequests()
	        .antMatchers("/manager/**").hasRole("MANAGER")
	        .antMatchers("/employee/**").hasRole("EMP")
	        //.antMatchers("/existedEmp").hasAnyRole("USER","ADMIN","EMP")
	        .and()
	        .authorizeRequests()
	        .antMatchers("/oauth/**", "/login/**")
	                .permitAll()																														.anyRequest().authenticated().and().formLogin()
	             // .and().authorizeRequests().anyRequest().authenticated().and().httpBasic() 
	                 .and().oauth2Login(login -> login.defaultSuccessUrl("http://localhost:3000/dashboard"));
	;
	}
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder getpasEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
