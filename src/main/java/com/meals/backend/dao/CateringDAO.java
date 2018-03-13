package com.meals.backend.dao;

import java.util.Date;
import java.util.List;

import com.meals.backend.model.Catering;

public interface CateringDAO {
	List<Catering> getLstByDepartment(List<String> staffIds, Date date);
	Boolean saveOrUpdate(Catering obj);
}
