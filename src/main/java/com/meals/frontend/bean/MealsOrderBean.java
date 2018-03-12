package com.meals.frontend.bean;

import java.util.Date;

public class MealsOrderBean {
	private String staffId;
	private String staffName;
	private Integer mealTimeId;
	private Integer locationId;
	private Integer mealId;
	private Integer deptId;
	private Date cateringDate;

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public Integer getMealTimeId() {
		return mealTimeId;
	}

	public void setMealTimeId(Integer mealTimeId) {
		this.mealTimeId = mealTimeId;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public Integer getMealId() {
		return mealId;
	}

	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public Date getCateringDate() {
		return cateringDate;
	}

	public void setCateringDate(Date cateringDate) {
		this.cateringDate = cateringDate;
	}

}
