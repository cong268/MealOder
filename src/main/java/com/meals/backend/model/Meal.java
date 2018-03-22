package com.meals.backend.model;
// Generated Mar 20, 2018 2:44:46 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Meal generated by hbm2java
 */
@Entity
@Table(name = "Meal"
 ,schema="dbo"
 ,catalog="nsrp"
)
public class Meal implements java.io.Serializable {

	private int mealId;
	private String mealName;
	private String description;

	public Meal() {
	}

	public Meal(int mealId) {
		this.mealId = mealId;
	}

	public Meal(int mealId, String mealName, String description) {
		this.mealId = mealId;
		this.mealName = mealName;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "MealId", unique = true, nullable = false)
	public int getMealId() {
		return this.mealId;
	}

	public void setMealId(int mealId) {
		this.mealId = mealId;
	}

	@Column(name = "MealName", length = 30)
	public String getMealName() {
		return this.mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	@Column(name = "Description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
