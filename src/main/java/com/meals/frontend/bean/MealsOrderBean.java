package com.meals.frontend.bean;

public class MealsOrderBean {
	private String staffId;
	private String staffName;
	private Integer mealTimeId;
	private Integer locationId;
	private Integer mealId;
	private Integer departmentId;
	private String dateMeal;
	private Boolean catered;
	private Boolean status;
	private Boolean ordered;
	private Boolean disable;

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

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDateMeal() {
		return dateMeal;
	}

	public void setDateMeal(String dateMeal) {
		this.dateMeal = dateMeal;
	}

	public Boolean getCatered() {
		return catered;
	}

	public void setCatered(Boolean catered) {
		this.catered = catered;
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

	public Boolean getDisable() {
		return disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}

}
