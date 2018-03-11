package com.meals.frontend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.meals.backend.dao.ConstantDAO;
import com.meals.backend.dao.StaffDAO;
import com.meals.backend.dao.UserDAO;
import com.meals.backend.model.Department;
import com.meals.backend.model.Staff;
import com.meals.backend.model.User;
import com.meals.backend.model.UserRole;
import com.meals.frontend.bean.StaffBean;
import com.meals.frontend.bean.UserBean;
import com.meals.frontend.service.MealsService;

public class MealsServiceImpl implements MealsService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private StaffDAO staffDAO;
	@Autowired
	private ConstantDAO constantDAO;

	public StaffBean getStaffByUserId(Integer userId) {
		StaffBean bean = new StaffBean();
		Staff staff = staffDAO.getStaffByUserId(userId);
		if (staff != null) {
			bean.setStaffId(staff.getStaffId());
			bean.setStaffName(staff.getStaffName());
			bean.setJobTitle(staff.getJobTitle());
			if (staff.getDeptId() != null) {
				Department depart = constantDAO.getDepartment(staff.getDeptId());
				bean.setDepartment(depart.getDeptName());
			}
		}
		return bean;
	}

	public UserBean checkUserLogin(String userName, String passWord) {
		UserBean bean = new UserBean();
		User user = userDAO.getUser(userName, passWord);
		if (user != null) {
			bean.setUserId(user.getUserId());
			bean.setStaffId(user.getStaffId());
			bean.setUserName(user.getUserName());
			bean.setPassword(user.getPassword());
			bean.setDisable(user.getDisable());
			if (user.getUserRoleId() != null){
				UserRole role = constantDAO.getRoleById(user.getUserRoleId());
				if (role != null) {
					bean.setUserRole(role.getName());
				}
			}
		}
		return bean;
	}

}
