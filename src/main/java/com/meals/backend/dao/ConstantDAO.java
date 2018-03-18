package com.meals.backend.dao;

import java.util.List;

import com.meals.backend.model.Allcode;
import com.meals.backend.model.Department;
import com.meals.backend.model.Location;
import com.meals.backend.model.Userrole;

public interface ConstantDAO {
	Department getDepartment (Integer id);
	Userrole getRoleById (Integer id);
	Location getLocationById (Integer id);
	List<Department> getAllDepartment();
	List<Location> getAllLocation();
	List<Userrole> getAllRole();
	List<Allcode> getLstByCode (String codeName);
}
