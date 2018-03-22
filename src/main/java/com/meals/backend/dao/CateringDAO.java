package com.meals.backend.dao;

import java.util.Date;
import java.util.List;

import com.meals.backend.model.Catering;

public interface CateringDAO {
	List<Catering> getLstByOder (Integer departId, Date cateringDate);
	List<Catering> getLstByStatus (Date cateringDate);
	List<Catering> getLstByDate (Date  fromDate, Date toDate);
	List<Catering> getByStaffAndDate (String staffId, Date fromDate, Date toDate);
	List<Object[]> getLstAndCount(Date fromDate, Date toDate);
	List<Catering> getByDepartAndDate (Integer deptId, Date  fromDate, Date toDate); 
	Catering getCateringById (String staffId, Date date, Integer mealTimeId);
	Boolean saveOrUpdate(Catering obj);
	Boolean saveList(List<Catering> lst);
	Boolean deleteCateringByStaff (String staffId,Date fromDate, Date toDate , Boolean ordered, Boolean status, Boolean catered);
	Boolean deleteCateringByManager (Integer deptId, Date date);
	Boolean updateByAdmin (String staffId, Date date);
	Boolean deleteById (String staffId,Integer mealTimeId, Date cateringDate);
}
