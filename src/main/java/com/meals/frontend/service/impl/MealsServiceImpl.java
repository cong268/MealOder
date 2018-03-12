package com.meals.frontend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meals.backend.dao.ConstantDAO;
import com.meals.backend.dao.StaffDAO;
import com.meals.backend.dao.UserDAO;
import com.meals.backend.model.Department;
import com.meals.backend.model.Location;
import com.meals.backend.model.Meal;
import com.meals.backend.model.MealTime;
import com.meals.backend.model.Shift;
import com.meals.backend.model.Staff;
import com.meals.backend.model.User;
import com.meals.backend.model.UserRole;
import com.meals.frontend.bean.DataBean;
import com.meals.frontend.bean.DepartmentBean;
import com.meals.frontend.bean.LocationBean;
import com.meals.frontend.bean.MealBean;
import com.meals.frontend.bean.MealTimeBean;
import com.meals.frontend.bean.MealsOrderBean;
import com.meals.frontend.bean.ShiftBean;
import com.meals.frontend.bean.StaffBean;
import com.meals.frontend.bean.UserBean;
import com.meals.frontend.service.MealsService;

@Service
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
				bean.setDepartId(depart.getDeptId());
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
			if (user.getUserRoleId() != null) {
				UserRole role = constantDAO.getRoleById(user.getUserRoleId());
				if (role != null) {
					bean.setUserRole(role.getName());
				}
			}
		}
		return bean;
	}

	public DataBean getLstOrderByDepart(Integer departId) {
		DataBean bean = getConstanBean();
		List<MealsOrderBean> lstOrder = new ArrayList<>();
		List<Staff> lstStaff = staffDAO.getStaffByDepart(departId);
		if (lstStaff != null && !lstStaff.isEmpty()) {
			for (Staff obj : lstStaff) {
				MealsOrderBean orderBean = new MealsOrderBean();
				orderBean.setStaffId(obj.getStaffId());
				orderBean.setStaffName(obj.getStaffName());
				lstOrder.add(orderBean);
			}
		}
		bean.setListMealOder(lstOrder);
		return bean;
	}

	private DataBean getConstanBean() {
		DataBean bean = new DataBean();
		List<MealTimeBean> lstMealTime = new ArrayList<>();
		List<MealBean> lstMealType = new ArrayList<>();
		List<LocationBean> lstLocation = new ArrayList<>();
		List<DepartmentBean> lstDepartMent = new ArrayList<>();
		List<ShiftBean> lstShift = new ArrayList<>();
		List<Department> lstDepart = constantDAO.getAllDepart();
		if (lstDepart != null && !lstDepart.isEmpty()) {
			for (Department obj : lstDepart) {
				lstDepartMent.add(new DepartmentBean(obj.getDeptId(), obj.getDeptName()));
			}
		}
		List<MealTime> lstMealTimeDAO = constantDAO.getAllMealTime();
		if (lstMealTimeDAO != null && !lstMealTimeDAO.isEmpty()) {
			for (MealTime obj : lstMealTimeDAO) {
				lstMealTime.add(new MealTimeBean(obj.getMealTimeId(), obj.getMealTimeName()));
			}
		}
		List<Meal> lstMealDAO = constantDAO.getAllMeal();
		if (lstMealDAO != null && !lstMealDAO.isEmpty()) {
			for (Meal obj : lstMealDAO) {
				lstMealType.add(new MealBean(obj.getMealId(), obj.getMealName()));
			}
		}
		List<Location> lstLocationDAO = constantDAO.getAllLocation();
		if (lstLocationDAO != null && !lstLocationDAO.isEmpty()) {
			for (Location obj : lstLocationDAO) {
				lstLocation.add(new LocationBean(obj.getLocationId(), obj.getLocationName()));
			}
		}
		List<Shift> lstShiftDAO = constantDAO.getAllShift();
		if (lstShiftDAO != null && !lstShiftDAO.isEmpty()) {
			for (Shift obj : lstShiftDAO) {
				lstShift.add(new ShiftBean(obj.getShiftId(), obj.getShiftName()));
			}
		}
		bean.setLstDepartMent(lstDepartMent);
		bean.setLstLocation(lstLocation);
		bean.setLstMealTime(lstMealTime);
		bean.setLstMealType(lstMealType);
		bean.setLstShift(lstShift);
		return bean;
	}
}
