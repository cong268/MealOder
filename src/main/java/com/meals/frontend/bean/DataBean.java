package com.meals.frontend.bean;

import java.util.List;

public class DataBean {
	private List<MealTimeBean> lstMealTime;
	private List<MealTypeBean> lstMealType;
	private List<LocationBean> lstLocation;
	private List<DepartmentBean> lstDepartMent;
	private List<MealsOrderBean> listMealOder;

	public List<MealTimeBean> getLstMealTime() {
		return lstMealTime;
	}

	public void setLstMealTime(List<MealTimeBean> lstMealTime) {
		this.lstMealTime = lstMealTime;
	}

	public List<MealTypeBean> getLstMealType() {
		return lstMealType;
	}

	public void setLstMealType(List<MealTypeBean> lstMealType) {
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
