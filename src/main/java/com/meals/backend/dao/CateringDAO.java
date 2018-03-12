package com.meals.backend.dao;

import java.util.List;

import com.meals.backend.model.Catering;

public interface CateringDAO {
//	List<Catering> getLstBy
	Boolean saveOrUpdate(Catering obj);
}
