package com.clayfin.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	private String roleName;
	private String roleActivities;
	
	/*
	 * @OneToOne(mappedBy = "role") Employee employee;
	 */
	
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleActivities() {
		return roleActivities;
	}

	public void setRoleActivities(String roleActivities) {
		this.roleActivities = roleActivities;
	}

	

	public Role(Integer id, String roleName, String roleActivities) {
		super();
		Id = id;
		this.roleName = roleName;
		this.roleActivities = roleActivities;
		
	}

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Role [Id=" + Id + ", roleName=" + roleName + ", roleActivities=" + roleActivities  ;
	}

	
	
	
	
	
	
}
