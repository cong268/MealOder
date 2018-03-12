package com.meals.frontend.bean;

public class LocationBean {
	private Integer locationId;
	private String locationName;
	private String description;

	public LocationBean(Integer locationId, String locationName) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
