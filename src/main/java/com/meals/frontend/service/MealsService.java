package com.meals.frontend.service;

import com.meals.frontend.bean.StaffBean;
import com.meals.frontend.bean.UserBean;

public interface MealsService {
	StaffBean getStaffByUserId(Integer userId);

	UserBean checkUserLogin(String userName, String passWord);
}
