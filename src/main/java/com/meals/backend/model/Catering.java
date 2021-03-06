package com.meals.backend.model;
// Generated Mar 20, 2018 2:44:46 PM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Catering generated by hbm2java
 */
@Entity
@Table(name = "Catering"
 ,schema="dbo"
 ,catalog="nsrp"
)
public class Catering implements java.io.Serializable {

	private CateringId id;
	private int mealId;
	private int locationId;
	private int deptId;
	private boolean catered;
	private Date cateringTime;
	private boolean status;
	private boolean ordered;
	private boolean disable;

	public Catering() {
	}

	public Catering(CateringId id, int mealId, int locationId, int deptId, boolean catered, Date cateringTime,
			boolean status, boolean ordered) {
		this.id = id;
		this.mealId = mealId;
		this.locationId = locationId;
		this.deptId = deptId;
		this.catered = catered;
		this.cateringTime = cateringTime;
		this.status = status;
		this.ordered = ordered;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "staffId", column = @Column(name = "StaffId", nullable = false, length = 12)),
			@AttributeOverride(name = "mealTimeId", column = @Column(name = "MealTimeId", nullable = false)),
			@AttributeOverride(name = "cateringDate", column = @Column(name = "CateringDate", nullable = false, length = 10)) })
	public CateringId getId() {
		return this.id;
	}

	public void setId(CateringId id) {
		this.id = id;
	}

	@Column(name = "MealId", nullable = false)
	public int getMealId() {
		return this.mealId;
	}

	public void setMealId(int mealId) {
		this.mealId = mealId;
	}

	@Column(name = "LocationId", nullable = false)
	public int getLocationId() {
		return this.locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	@Column(name = "DeptId", nullable = false)
	public int getDeptId() {
		return this.deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	@Column(name = "Catered", nullable = false)
	public boolean isCatered() {
		return this.catered;
	}

	public void setCatered(boolean catered) {
		this.catered = catered;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "CateringTime", nullable = false, length = 14)
	public Date getCateringTime() {
		return this.cateringTime;
	}

	public void setCateringTime(Date cateringTime) {
		this.cateringTime = cateringTime;
	}

	@Column(name = "Status", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Column(name = "Ordered", nullable = false)
	public boolean isOrdered() {
		return this.ordered;
	}

	public void setOrdered(boolean ordered) {
		this.ordered = ordered;
	}

	@Column(name = "Disable", nullable = false)
	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

}
