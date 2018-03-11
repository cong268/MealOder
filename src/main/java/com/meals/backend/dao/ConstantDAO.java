package com.meals.backend.dao;

import com.meals.backend.model.Department;
import com.meals.backend.model.UserRole;

public interface ConstantDAO {
	Department getDepartment (Integer id);
	UserRole getRoleById (Integer id);
}
