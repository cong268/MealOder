package com.meals.backend.dao;

import java.util.List;

import com.meals.backend.model.Staff;

public interface StaffDAO {
	Staff getStaffByUserId (Integer userId);
	List<Staff> getStaffByDepart(Integer departId);
}
