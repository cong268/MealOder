package com.meals.frontend.service;

import java.util.List;

import com.meals.frontend.bean.DataBean;
import com.meals.frontend.bean.MealsOrderBean;
import com.meals.frontend.bean.StaffBean;
import com.meals.frontend.bean.UserBean;

public interface MealsService {
	StaffBean getStaffByUserId(Integer userId);
	UserBean checkUserLogin(String userName, String passWord);
	DataBean getMealByStaff(Integer userId);
	DataBean getLstOrderByDepart(Integer departId);
	Boolean saveCatering(String userRole, List<MealsOrderBean> listMealOder, String date);
}
