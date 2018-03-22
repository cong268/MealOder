package com.meals.frontend.service;

import java.util.Date;
import java.util.List;

import com.meals.frontend.bean.CanteenExportBean;
import com.meals.frontend.bean.DataBean;
import com.meals.frontend.bean.DataCateringExport;
import com.meals.frontend.bean.DepartmentBean;
import com.meals.frontend.bean.ListObjectBean;
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
	DataBean getLstShowManager(Integer userId,String fromDate, String toDate);
	String saveStaff (StaffBean bean, Boolean isNew);
	String saveUser (UserBean bean);
	public String changePassword(String userName, String passWord, String newPassword);
	List<RoleBean> getAllRole();
	List<DepartmentBean> getAllDepart();
	List<CanteenExportBean> getLstAndCount(Date fromDate, Date toDate);
	Boolean saveCateringByStaff (Integer userId, String userRole, List<MealsOrderBean> listMealOder, String fromDate, String toDate);
	Boolean saveCateringByManager(ListObjectBean bean, String date, Integer departId);
	Boolean saveCateringByAdmin(String date, ListObjectBean bean);
	List<MealsOrderBean> getHistoryStaff(Integer userId, String fromDate, String toDate);
	List<DataCateringExport> getDataExportByRole (String userRole,Integer deptId, String staffId, Date fromDate, Date toDate);
	Boolean deleteCatering(MealsOrderBean bean);
	Boolean updateCatering (MealsOrderBean bean);
	Boolean checkStaffId(String staffId);
	String getDepartNameById (Integer departId);
}
