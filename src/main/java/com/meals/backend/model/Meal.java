package com.meals.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "meal")
public class Meal {
	@Column(name = "MealId", nullable = false)
	private String mealId;
	@Column(name = "MealName", nullable = false)
	private String mealName;
	@Column(name = "MealId", nullable = false)
	private String locationId;
	@Column(name = "Description")
	private String description;

	public String getMealId() {
		return mealId;
	}

	public void setMealId(String mealId) {
		this.mealId = mealId;
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
