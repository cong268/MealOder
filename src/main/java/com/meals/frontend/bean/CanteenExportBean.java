package com.meals.frontend.bean;

public class CanteenExportBean {
	private String date;
	private String department;
	private String mealType;
	private String mealTime;
	private String location;
	private Long quantity;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

	public String getMealTime() {
		return mealTime;
	}

	public void setMealTime(String mealTime) {
		this.mealTime = mealTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

}
