package com.meals.frontend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.meals.backend.dao.ConstantDAO;
import com.meals.backend.dao.StaffDAO;
import com.meals.backend.model.Department;
import com.meals.backend.model.Staff;
import com.meals.frontend.bean.StaffBean;
import com.meals.frontend.service.MealsService;

public class MealsServiceImpl implements MealsService {
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

}
