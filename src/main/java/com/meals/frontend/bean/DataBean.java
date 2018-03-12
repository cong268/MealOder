package com.meals.frontend.bean;

import java.util.List;

public class DataBean {
	private List<MealTimeBean> lstMealTime;
	private List<MealBean> lstMealType;
	private List<LocationBean> lstLocation;
	private List<DepartmentBean> lstDepartMent;
	private List<ShiftBean> lstShift;
	private List<MealsOrderBean> listMealOder;

	public List<MealTimeBean> getLstMealTime() {
		return lstMealTime;
	}

	public void setLstMealTime(List<MealTimeBean> lstMealTime) {
		this.lstMealTime = lstMealTime;
	}

	public List<MealBean> getLstMealType() {
		return lstMealType;
	}

	public void setLstMealType(List<MealBean> lstMealType) {
		this.lstMealType = lstMealType;
	}

	public List<LocationBean> getLstLocation() {
		return lstLocation;
	}

	public void setLstLocation(List<LocationBean> lstLocation) {
		this.lstLocation = lstLocation;
	}

	public List<DepartmentBean> getLstDepartMent() {
		return lstDepartMent;
	}

	public void setLstDepartMent(List<DepartmentBean> lstDepartMent) {
		this.lstDepartMent = lstDepartMent;
	}

	public List<ShiftBean> getLstShift() {
		return lstShift;
	}

	public void setLstShift(List<ShiftBean> lstShift) {
		this.lstShift = lstShift;
	}

	public List<MealsOrderBean> getListMealOder() {
		return listMealOder;
	}

	public void setListMealOder(List<MealsOrderBean> listMealOder) {
		this.listMealOder = listMealOder;
	}

}
