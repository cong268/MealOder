package com.meals.frontend.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meals.backend.dao.CateringDAO;
import com.meals.backend.dao.ConstantDAO;
import com.meals.backend.dao.StaffDAO;
import com.meals.backend.dao.UserDAO;
import com.meals.backend.model.Catering;
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
import com.meals.frontend.until.ConstanKey;
import com.meals.frontend.until.FunctionUtils;

@Service
public class MealsServiceImpl implements MealsService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private StaffDAO staffDAO;
	@Autowired
	private ConstantDAO constantDAO;
	@Autowired
	private CateringDAO cateringDAO;

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
			return bean;
		}
		return null;
	}

	public DataBean getLstOrderByDepart(Integer userId, String date) {
		DataBean bean = new DataBean();
		Date dateTime = FunctionUtils.convertDateByFormatLocal(date, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		List<MealsOrderBean> lstOrder = new ArrayList<>();
		Staff staff = staffDAO.getStaffByUserId(userId);
		if (staff != null) {
			List<Staff> lstStaff = staffDAO.getStaffByDepart(staff.getDeptId());
			if (lstStaff != null && !lstStaff.isEmpty()) {
				for (Staff obj : lstStaff) {
					Catering catering = cateringDAO.getByStaffId(obj.getStaffId(), dateTime);
					if (catering == null || !catering.getOrdered()) {
						MealsOrderBean orderBean = new MealsOrderBean();
						orderBean.setStaffId(obj.getStaffId());
						orderBean.setStaffName(obj.getStaffName());
						lstOrder.add(orderBean);
					}
				}
			}
		}
		bean.setListMealOder(lstOrder);
		return bean;
	}

	@Override
	public Boolean saveCatering(String userRole, List<MealsOrderBean> listMealOder, String date) {
		Date cateringDate = FunctionUtils.convertDateByFormatLocal(date, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date cateringTime = new Date();
		if (listMealOder != null && !listMealOder.isEmpty()) {
			for (MealsOrderBean obj : listMealOder) {
				Catering dto = new Catering();
				dto.setStaffId(obj.getStaffId());
				dto.setMealId(obj.getMealId());
				dto.setMealTimeId(obj.getMealTimeId());
				dto.setLocationId(obj.getLocationId());
				dto.setShiftId(obj.getShiftId());
				dto.setDepartId(obj.getDepartmentId());
				dto.setCateringDate(cateringDate);
				dto.setCateringTime(cateringTime);
				if (userRole != null && userRole.equals(ConstanKey.ROLE.ROLE_ADMIN)) {
					dto.setCatered(true);
				} else if (userRole != null && userRole.equals(ConstanKey.ROLE.ROLE_MANAGER)) {
					dto.setStatus(true);
				} else {
					dto.setOrdered(true);
				}
				cateringDAO.saveOrUpdate(dto);
			}
		}
		return true;
	}

	@Override
	public DataBean getMealByStaff(Integer userId) {
		DataBean bean = new DataBean();
		List<MealTimeBean> lstMealTime = getMealTime();
		List<MealBean> lstMealType = getLstMeal();
		List<LocationBean> lstLocation = getLocation();
		List<DepartmentBean> lstDepartMent = getDepartMent();
		List<ShiftBean> lstShift = getShift();
		MealsOrderBean orderBean = new MealsOrderBean();
		Staff staff = staffDAO.getStaffByUserId(userId);
		if (staff != null) {
			orderBean.setStaffId(staff.getStaffId());
			orderBean.setStaffName(staff.getStaffName());
			if (lstDepartMent != null && !lstDepartMent.isEmpty()) {
				orderBean.setDepartmentId(lstDepartMent.get(0).getDeptId());
			}
			if (lstMealTime != null && !lstMealTime.isEmpty()) {
				orderBean.setMealTimeId(lstMealTime.get(0).getMealTimeId());
			}
			if (lstMealType != null && !lstMealType.isEmpty()) {
				orderBean.setMealId(lstMealType.get(0).getMealId());
			}
			if (lstLocation != null && !lstLocation.isEmpty()) {
				orderBean.setLocationId(lstLocation.get(0).getLocationId());
			}
			if (lstShift != null && !lstShift.isEmpty()) {
				orderBean.setShiftId(lstShift.get(0).getShiftId());
			}
		}
		bean.setLstDepartMent(lstDepartMent);
		bean.setLstLocation(lstLocation);
		bean.setLstMealTime(lstMealTime);
		bean.setLstMealType(lstMealType);
		bean.setLstShift(lstShift);
		bean.setListMealOder(Arrays.asList(orderBean));
		return bean;
	}

	@Override
	public DataBean getLstByOder(Integer userId, String date) {
		DataBean bean = new DataBean();
		List<MealTimeBean> lstMealTime = getMealTime();
		List<MealBean> lstMealType = getLstMeal();
		List<LocationBean> lstLocation = getLocation();
		List<DepartmentBean> lstDepartMent = getDepartMent();
		List<ShiftBean> lstShift = getShift();
		List<MealsOrderBean> lstOder = new ArrayList<>();
		Date dateTime = FunctionUtils.convertDateByFormatLocal(date, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Staff staff = staffDAO.getStaffByUserId(userId);
		if (staff != null) {
			List<Catering> lst = cateringDAO.getLstByOder(staff.getDeptId(), dateTime);
			if (lst != null && !lst.isEmpty()) {
				for (Catering catering : lst) {
					Staff obj = staffDAO.getByStaff(catering.getStaffId());
					if (obj != null) {
						MealsOrderBean oderBean = new MealsOrderBean();
						oderBean.setStaffId(obj.getStaffId());
						oderBean.setStaffName(obj.getStaffName());
						oderBean.setDepartmentId(catering.getDepartId());
						oderBean.setMealTimeId(catering.getMealTimeId());
						oderBean.setMealId(catering.getMealId());
						oderBean.setLocationId(catering.getLocationId());
						oderBean.setShiftId(catering.getShiftId());
						lstOder.add(oderBean);
					}
				}
			}
		}
		bean.setListMealOder(lstOder);
		bean.setLstDepartMent(lstDepartMent);
		bean.setLstLocation(lstLocation);
		bean.setLstMealTime(lstMealTime);
		bean.setLstMealType(lstMealType);
		bean.setLstShift(lstShift);
		return bean;
	}

	@Override
	public DataBean getLstByStatus(String date) {
		DataBean bean = new DataBean();
		List<MealTimeBean> lstMealTime = getMealTime();
		List<MealBean> lstMealType = getLstMeal();
		List<LocationBean> lstLocation = getLocation();
		List<DepartmentBean> lstDepartMent = getDepartMent();
		List<ShiftBean> lstShift = getShift();
		List<MealsOrderBean> lstOder = new ArrayList<>();
		Date dateTime = FunctionUtils.convertDateByFormatLocal(date, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		if(dateTime != null){
			List<Catering> lst = cateringDAO.getLstByStatus(dateTime);
			if (lst != null && !lst.isEmpty()) {
				for (Catering catering : lst) {
					Staff obj = staffDAO.getByStaff(catering.getStaffId());
					if (obj != null) {
						MealsOrderBean oderBean = new MealsOrderBean();
						oderBean.setStaffId(obj.getStaffId());
						oderBean.setStaffName(obj.getStaffName());
						oderBean.setDepartmentId(catering.getDepartId());
						oderBean.setMealTimeId(catering.getMealTimeId());
						oderBean.setMealId(catering.getMealId());
						oderBean.setLocationId(catering.getLocationId());
						oderBean.setShiftId(catering.getShiftId());
						lstOder.add(oderBean);
					}
				}
			}
		}
		bean.setListMealOder(lstOder);
		bean.setLstDepartMent(lstDepartMent);
		bean.setLstLocation(lstLocation);
		bean.setLstMealTime(lstMealTime);
		bean.setLstMealType(lstMealType);
		bean.setLstShift(lstShift);
		return bean;
	}
	
	@Override
	public DataBean getLstByDate(String fromDate, String toDate) {
		DataBean bean = new DataBean();
		List<MealTimeBean> lstMealTime = getMealTime();
		List<MealBean> lstMealType = getLstMeal();
		List<LocationBean> lstLocation = getLocation();
		List<DepartmentBean> lstDepartMent = getDepartMent();
		List<ShiftBean> lstShift = getShift();
		List<MealsOrderBean> lstOder = new ArrayList<>();
		Date fromTime = FunctionUtils.convertDateByFormatLocal(fromDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date toTime = FunctionUtils.convertDateByFormatLocal(toDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		if(fromTime != null && toTime != null){
			List<Catering> lst = cateringDAO.getLstByDate(fromTime, toTime);
			if (lst != null && !lst.isEmpty()) {
				for (Catering catering : lst) {
					Staff obj = staffDAO.getByStaff(catering.getStaffId());
					if (obj != null) {
						MealsOrderBean oderBean = new MealsOrderBean();
						oderBean.setStaffId(obj.getStaffId());
						oderBean.setStaffName(obj.getStaffName());
						oderBean.setDepartmentId(catering.getDepartId());
						oderBean.setMealTimeId(catering.getMealTimeId());
						oderBean.setMealId(catering.getMealId());
						oderBean.setLocationId(catering.getLocationId());
						oderBean.setShiftId(catering.getShiftId());
						lstOder.add(oderBean);
					}
				}
			}
		}
		bean.setListMealOder(lstOder);
		bean.setLstDepartMent(lstDepartMent);
		bean.setLstLocation(lstLocation);
		bean.setLstMealTime(lstMealTime);
		bean.setLstMealType(lstMealType);
		bean.setLstShift(lstShift);
		return bean;
	}
	
	private List<MealTimeBean> getMealTime() {
		List<MealTimeBean> lstMealTime = new ArrayList<>();
		List<MealTime> lstMealTimeDAO = constantDAO.getAllMealTime();
		if (lstMealTimeDAO != null && !lstMealTimeDAO.isEmpty()) {
			for (MealTime obj : lstMealTimeDAO) {
				lstMealTime.add(new MealTimeBean(obj.getMealTimeId(), obj.getMealTimeName()));
			}
		}
		return lstMealTime;
	}

	private List<MealBean> getLstMeal() {
		List<MealBean> lstMealType = new ArrayList<>();
		List<Meal> lstMealDAO = constantDAO.getAllMeal();
		if (lstMealDAO != null && !lstMealDAO.isEmpty()) {
			for (Meal obj : lstMealDAO) {
				lstMealType.add(new MealBean(obj.getMealId(), obj.getMealName()));
			}
		}
		return lstMealType;
	}

	private List<LocationBean> getLocation() {
		List<LocationBean> lstLocation = new ArrayList<>();
		List<Location> lstLocationDAO = constantDAO.getAllLocation();
		if (lstLocationDAO != null && !lstLocationDAO.isEmpty()) {
			for (Location obj : lstLocationDAO) {
				lstLocation.add(new LocationBean(obj.getLocationId(), obj.getLocationName()));
			}
		}
		return lstLocation;
	}

	private List<DepartmentBean> getDepartMent() {
		List<DepartmentBean> lstDepartMent = new ArrayList<>();
		List<Department> lstDepart = constantDAO.getAllDepart();
		if (lstDepart != null && !lstDepart.isEmpty()) {
			for (Department obj : lstDepart) {
				lstDepartMent.add(new DepartmentBean(obj.getDeptId(), obj.getDeptName()));
			}
		}
		return lstDepartMent;
	}

	private List<ShiftBean> getShift() {
		List<ShiftBean> lstShift = new ArrayList<>();
		List<Shift> lstShiftDAO = constantDAO.getAllShift();
		if (lstShiftDAO != null && !lstShiftDAO.isEmpty()) {
			for (Shift obj : lstShiftDAO) {
				lstShift.add(new ShiftBean(obj.getShiftId(), obj.getShiftName()));
			}
		}
		return lstShift;
	}
}
