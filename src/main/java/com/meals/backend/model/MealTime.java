package com.meals.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mealtime")
public class MealTime {
	@Id
	@Column(name = "MealTimeId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mealTimeId;
	@Column(name = "MealTimeName", nullable = false)
	private String mealTimeName;
	@Column(name = "Description")
	private String description;

	public int getMealTimeId() {
		return mealTimeId;
	}

	public void setMealTimeId(int mealTimeId) {
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
