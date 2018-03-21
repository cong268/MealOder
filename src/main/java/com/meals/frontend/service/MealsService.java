package com.meals.frontend.service;

import java.util.List;

import com.meals.frontend.bean.CanteenExportBean;
import com.meals.frontend.bean.DataBean;
import com.meals.frontend.bean.DataCateringExport;
import com.meals.frontend.bean.MealsOrderBean;
import com.meals.frontend.bean.RoleBean;
import com.meals.frontend.bean.StaffBean;
import com.meals.frontend.bean.UserBean;

public interface MealsService {
	StaffBean getStaffByUserId(Integer userId);
	UserBean checkUserLogin(String userName, String passWord);
	DataBean getMealByStaff(Integer userId);
	DataBean getLstOrderByDepart(Integer departId);
	DataBean getLstByOder(Integer userId, String date);
	DataBean getLstByStatus (String date);
	DataBean getLstByDate (String fromDate,String toDate);
	Boolean saveStaff (StaffBean bean);
	Boolean saveUser (UserBean bean);
	List<RoleBean> getAllRole();
	List<CanteenExportBean> getLstAndCount(String fromDate, String toDate);
	Boolean saveCateringByStaff (Integer userId, String userRole, List<MealsOrderBean> listMealOder, String fromDate, String toDate);
	Boolean saveCateringByManager(Integer departId,List<MealsOrderBean> listMealOder, String date);
	Boolean updateStatusByAdmin(Integer userId, String date);
	List<MealsOrderBean> getHistoryStaff(Integer userId, String fromDate, String toDate);
	List<DataCateringExport> getDataExportByManager (Integer userId, String staffId, String fromDate, String toDate);
}
