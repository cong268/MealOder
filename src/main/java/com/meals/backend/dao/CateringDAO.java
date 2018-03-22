package com.meals.backend.dao;

import java.util.Date;
import java.util.List;

import com.meals.backend.model.Catering;

public interface CateringDAO {
	List<Catering> getLstByOder (Integer departId, Date cateringDate);
	List<Catering> getLstByStatus (Date cateringDate);
	List<Catering> getLstByDate (Date  fromDate, Date toDate);
	List<Catering> getHistoryStaff (String staffId, Date fromDate, Date toDate);
	List<Catering> getExportStaffByManager (String staffId, Date fromDate, Date toDate);
	List<Catering> getExportStaffByAdmin (String staffId, Date fromDate, Date toDate);
	List<Catering> getExportDepartByManager (Integer deptId, Date  fromDate, Date toDate);
	List<Catering> getExportDepartByAdmin (Integer deptId, Date  fromDate, Date toDate);
	List<Object[]> getLstAndCount(Date fromDate, Date toDate);
	Catering getCateringById (String staffId, Date date, Integer mealTimeId);
	Boolean saveOrUpdate(Catering obj);
	Boolean saveList(List<Catering> lst);
	Boolean deleteById (String staffId,Integer mealTimeId, Date cateringDate);
	Boolean updateReject (String staffId, Date date, Integer mealTimeId);
	Boolean updateCatered (String staffId, Date date, Integer mealTimeId);
}
