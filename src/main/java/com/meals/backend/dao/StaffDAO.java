package com.meals.backend.dao;

import com.meals.backend.model.Staff;

public interface StaffDAO {
	Staff getStaffByUserId (Integer userId);
}
