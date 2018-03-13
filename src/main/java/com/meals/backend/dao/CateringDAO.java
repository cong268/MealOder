package com.meals.backend.dao;

import java.util.Date;
import java.util.List;

import com.meals.backend.model.Catering;

public interface CateringDAO {
	List<Catering> getLstByDepartment(List<String> staffIds, Date date);
	List<Catering> getLstAppro (List<String> staffLst, Date cateringDate);
	Boolean saveOrUpdate(Catering obj);
}
