package com.meals.frontend.bean;

import java.util.List;

public class DataBean {
	private List<MealBean> lstMealTime;
	private List<MealBean> lstMealType;
	private List<LocationBean> lstLocation;
	private List<DepartmentBean> lstDepartMent;
	private List<MealsOrderBean> listMealOder;

	public List<MealBean> getLstMealTime() {
		return lstMealTime;
	}

	public void setLstMealTime(List<MealBean> lstMealTime) {
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

	public List<MealsOrderBean> getListMealOder() {
		return listMealOder;
	}

	public void setListMealOder(List<MealsOrderBean> listMealOder) {
		this.listMealOder = listMealOder;
	}

}
