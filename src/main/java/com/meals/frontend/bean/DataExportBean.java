package com.meals.frontend.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class DataExportBean {
	private String fromDate;
	private String toDate;
	private Map<Date, List<MealsOrderBean>> mapDataByDate;
	private Map<Integer, String> mapDepartMent;
	private Map<Integer, String> mapLocation;

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

	public Map<Integer, String> getMapDepartMent() {
		return mapDepartMent;
	}

	public void setMapDepartMent(Map<Integer, String> mapDepartMent) {
		this.mapDepartMent = mapDepartMent;
	}

	public Map<Integer, String> getMapLocation() {
		return mapLocation;
	}

	public void setMapLocation(Map<Integer, String> mapLocation) {
		this.mapLocation = mapLocation;
	}

}
