package com.meals.frontend.bean;

public class MealsOrderBean {
	private String staffId;
	private String staffName;
	private MealTimeBean mealTime;
	private LocationBean location;
	private MealBean meal;
	private DepartmentBean department;
	private ShiftBean shift;

	// private Date cateringDate;
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

	public MealTimeBean getMealTime() {
		return mealTime;
	}

	public void setMealTime(MealTimeBean mealTime) {
		this.mealTime = mealTime;
	}

	public LocationBean getLocation() {
		return location;
	}

	public void setLocation(LocationBean location) {
		this.location = location;
	}

	public MealBean getMeal() {
		return meal;
	}

	public void setMeal(MealBean meal) {
		this.meal = meal;
	}

	public DepartmentBean getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentBean department) {
		this.department = department;
	}

	public ShiftBean getShift() {
		return shift;
	}

	public void setShift(ShiftBean shift) {
		this.shift = shift;
	}

}
