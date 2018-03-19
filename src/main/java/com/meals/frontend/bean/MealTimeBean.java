package com.meals.frontend.bean;

public class MealTimeBean {
	private Integer mealTimeId;
	private String mealTimeName;
	private String description;

	public MealTimeBean(Integer mealTimeId, String mealTimeName, String description) {
		super();
		this.mealTimeId = mealTimeId;
		this.mealTimeName = mealTimeName;
		this.description = description;
	}

	public Integer getMealTimeId() {
		return mealTimeId;
	}

	public void setMealTimeId(Integer mealTimeId) {
		this.mealTimeId = mealTimeId;
	}

	public String getMealTimeName() {
		return mealTimeName;
	}

	public void setMealTimeName(String mealTimeName) {
		this.mealTimeName = mealTimeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
