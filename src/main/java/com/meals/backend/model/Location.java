package com.meals.backend.model;
// Generated Mar 20, 2018 2:44:46 PM by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Location generated by hbm2java
 */
@Entity
@Table(name = "Location"
 ,schema="dbo"
 ,catalog="nsrp"
)
public class Location implements java.io.Serializable {

	private int locationId;
	private String locationName;
	private String description;

	public Location() {
	}

	public Location(int locationId, String locationName) {
		this.locationId = locationId;
		this.locationName = locationName;
	}

	public Location(int locationId, String locationName, String description) {
		this.locationId = locationId;
		this.locationName = locationName;
		this.description = description;
	}

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LocationId", unique = true, nullable = false)
	public int getLocationId() {
		return this.locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	@Column(name = "LocationName", nullable = false, length = 120)
	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	@Column(name = "Description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
