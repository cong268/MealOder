package com.meals.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "catering")
public class Catering {
	@Id
	@Column(name = "StaffId", nullable = false)
	private String staffId;
	@Column(name = "MealId", nullable = false)
	private Integer mealId;
	@Column(name = "LocationId")
	private Integer locationId;
	@Column(name = "MealTimeId", nullable = false)
	private Integer mealTimeId;
	@Column(name = "ShiftId", nullable = false)
	private Integer shiftId;
	@Column(name = "DepartId", nullable = false)
	private Integer departId;
	@Column(name = "CateringDate", nullable = false, columnDefinition = "DATE")
	private Date cateringDate;
	@Column(name = "Catered", nullable = false)
	private Boolean catered;
	@Column(name = "CateringTime", nullable = false, columnDefinition = "TIMESTAMP")
	private Date cateringTime;
	@Column(name = "Status", nullable = false)
	private Boolean status;
	@Column(name = "Ordered ", nullable = false)
	private Boolean ordered;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public Integer getMealId() {
		return mealId;
	}

	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getMealTimeId() {
		return mealTimeId;
	}

	public void setMealTimeId(Integer mealTimeId) {
		this.mealTimeId = mealTimeId;
	}

	public Integer getShiftId() {
		return shiftId;
	}

	public void setShiftId(Integer shiftId) {
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

	public Boolean getOrdered() {
		return ordered;
	}

	public void setOrdered(Boolean ordered) {
		this.ordered = ordered;
	}

	public Integer getDepartId() {
		return departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

}
