package com.meals.frontend.bean;

public class ShiftBean {
	private Integer shiftId;
	private String shiftName;
	private Boolean status;

	public ShiftBean(Integer shiftId, String shiftName) {
		super();
		this.shiftId = shiftId;
		this.shiftName = shiftName;
	}

	public Integer getShiftId() {
		return shiftId;
	}

	public void setShiftId(Integer shiftId) {
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
