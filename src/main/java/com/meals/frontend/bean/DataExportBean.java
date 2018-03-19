package com.meals.frontend.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class DataExportBean {
	private String fromDate;
	private String toDate;
	private Map<Date, List<MealsOrderBean>> mapDataByDate;
	private List<MealTimeBean> lstMealTime;
	private List<MealTypeBean> lstMealType;
	private List<LocationBean> lstLocation;
	private List<DepartmentBean> lstDepartMent;

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public Map<Date, List<MealsOrderBean>> getMapDataByDate() {
		return mapDataByDate;
	}

	public void setMapDataByDate(Map<Date, List<MealsOrderBean>> mapDataByDate) {
		this.mapDataByDate = mapDataByDate;
	}

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

}
