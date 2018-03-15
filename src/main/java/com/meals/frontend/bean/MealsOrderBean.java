package com.meals.frontend.bean;

public class MealsOrderBean {
	private String staffId;
	private String staffName;
	private Integer mealTimeId;
	private Integer locationId;
	private Integer mealId;
	private Integer departmentId;
	private Integer shiftId;
	private String dateMeal;

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

	public Integer getShiftId() {
		return shiftId;
	}

	public void setShiftId(Integer shiftId) {
		this.shiftId = shiftId;
	}

	public String getDateMeal() {
		return dateMeal;
	}

	public void setDateMeal(String dateMeal) {
		this.dateMeal = dateMeal;
	}

}
