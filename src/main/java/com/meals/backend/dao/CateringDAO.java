package com.meals.backend.dao;

import java.util.Date;
import java.util.List;

import com.meals.backend.model.Catering;

public interface CateringDAO {
	List<Catering> getLstByOder (Integer departId, Date cateringDate);
	List<Catering> getLstByStatus (Date cateringDate);
	List<Catering> getLstByDate (Date  fromDate, Date toDate);
	Catering getByStaffId (String staffId, Date date);
	Boolean saveOrUpdate(Catering obj);
}
