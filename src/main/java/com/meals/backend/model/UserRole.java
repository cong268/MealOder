package com.meals.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userrole")
public class UserRole {
	@Id
	@Column(name = "UserRoleID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userRoleID;
	@Column(name = "Name", nullable = false)
	private String name;
	@Column(name = "Description", columnDefinition = "TEXT")
	private String description;
	@Column(name = "Disabled", nullable = false )
	private Boolean disabled;

	public int getUserRoleID() {
		return userRoleID;
	}

	public void setUserRoleID(int userRoleID) {
		this.userRoleID = userRoleID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

}
