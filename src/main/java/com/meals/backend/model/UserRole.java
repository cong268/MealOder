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
	@Column(name = "UID")
	private String uID;
	@Column(name = "CODE")
	private String code;
	@Column(name = "Description", columnDefinition = "TEXT")
	private String description;
	@Column(name = "LastUpdated", columnDefinition = "DATE")
	private String lastUpdated;
	@Column(name = "Created", columnDefinition = "DATE")
	private String created;
	@Column(name = "PublicAccess")
	private Boolean publicAccess;
	@Column(name = "Disable", nullable = false)
	private Boolean disable;

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

	public String getuID() {
		return uID;
	}

	public void setuID(String uID) {
		this.uID = uID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public Boolean getPublicAccess() {
		return publicAccess;
	}

	public void setPublicAccess(Boolean publicAccess) {
		this.publicAccess = publicAccess;
	}

	public Boolean getDisable() {
		return disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}

}
