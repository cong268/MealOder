package com.meals.frontend.bean;

public class MealTypeBean {
	private Integer mealId;
	private String mealName;
	private String description;

	public MealTypeBean(Integer mealId, String mealName, String description) {
		super();
		this.mealId = mealId;
		this.mealName = mealName;
		this.description = description;
	}

	public Integer getMealId() {
		return mealId;
	}

	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
