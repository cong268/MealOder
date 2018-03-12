package com.meals.backend.dao;

import java.util.List;

import com.meals.backend.model.Department;
import com.meals.backend.model.Location;
import com.meals.backend.model.Meal;
import com.meals.backend.model.MealTime;
import com.meals.backend.model.Shift;
import com.meals.backend.model.UserRole;

public interface ConstantDAO {
	Department getDepartment (Integer id);
	UserRole getRoleById (Integer id);
	List<Department> getAllDepart();
	List<Location> getAllLocation();
	List<Meal> getAllMeal();
	List<MealTime> getAllMealTime();
	List<Shift> getAllShift();
}
