package com.meals.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "UserId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	@Column(name = "UserRoleId", nullable = false)
	private Integer userRoleId;
	@Column(name = "StaffId", nullable = false)
	private String staffId;
	@Column(name = "Disable")
	private Boolean disable;
	@Column(name = "LastLogin")
	private Date lastLogin;
	@Column(name = "UserName", nullable = false)
	private String userName;
	@Column(name = "Password" , nullable = false)
	private String password;
	@Column(name = "SelfRegistered")
	private String selfRegistered;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public Boolean getDisable() {
		return disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSelfRegistered() {
		return selfRegistered;
	}

	public void setSelfRegistered(String selfRegistered) {
		this.selfRegistered = selfRegistered;
	}

}
