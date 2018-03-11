package com.meals.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shift")
public class Shift {
	@Id
	@Column(name = "ShiftId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int shiftId;
	@Column(name = "ShiftName", nullable = false)
	private String shiftName;
	@Column(name = "Status")
	private Boolean status;

	public int getShiftId() {
		return shiftId;
	}

	public void setShiftId(int shiftId) {
		this.shiftId = shiftId;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
