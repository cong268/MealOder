package com.meals.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "catering")
public class Catering {
	@Column(name = "StaffId", nullable = false)
	private String staffId;
	@Column(name = "MealId", nullable = false)
	private String mealId;
	@Column(name = "LocationId")
	private Boolean locationId;
	@Column(name = "MealTimeId", nullable = false)
	private String mealTimeId;
	@Column(name = "ShiftId", nullable = false)
	private String shiftId;
	@Column(name = "CateringDate", nullable = false)
	@Type(type = "date")
	private Date cateringDate;
	@Column(name = "Catered", nullable = false)
	private Boolean catered;
	@Column(name = "CateringTime", nullable = false)
	@Type(type = "timestamp")
	private Date cateringTime;
	@Column(name = "Status", nullable = false)
	private Boolean status;
	@Column(name = "UserId")
	private String userId;
	@Column(name = "Order", nullable = false)
	private Boolean order;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getMealId() {
		return mealId;
	}

	public void setMealId(String mealId) {
		this.mealId = mealId;
	}

	public Boolean getLocationId() {
		return locationId;
	}

	public void setLocationId(Boolean locationId) {
		this.locationId = locationId;
	}

	public String getMealTimeId() {
		return mealTimeId;
	}

	public void setMealTimeId(String mealTimeId) {
		this.mealTimeId = mealTimeId;
	}

	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	public Date getCateringDate() {
		return cateringDate;
	}

	public void setCateringDate(Date cateringDate) {
		this.cateringDate = cateringDate;
	}

	public Boolean getCatered() {
		return catered;
	}

	public void setCatered(Boolean catered) {
		this.catered = catered;
	}

	public Date getCateringTime() {
		return cateringTime;
	}

	public void setCateringTime(Date cateringTime) {
		this.cateringTime = cateringTime;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getOrder() {
		return order;
	}

	public void setOrder(Boolean order) {
		this.order = order;
	}

}
