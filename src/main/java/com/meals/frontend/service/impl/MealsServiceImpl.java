package com.meals.frontend.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.meals.frontend.bean.DataExportBean;
import com.meals.frontend.bean.DepartmentBean;
import com.meals.frontend.bean.LocationBean;
import com.meals.frontend.bean.MealBean;
import com.meals.frontend.bean.MealTimeBean;
import com.meals.frontend.bean.MealsOrderBean;
import com.meals.frontend.bean.RoleBean;
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
		List<MealTimeBean> lstMealTime = getMealTime();
		List<MealBean> lstMealType = getLstMeal();
		List<LocationBean> lstLocation = getLocation();
		List<DepartmentBean> lstDepartMent = getDepartMent();
		List<ShiftBean> lstShift = getShift();
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
						lstOrder.add(orderBean);
					}
				}
			}
		}
		bean.setLstDepartMent(lstDepartMent);
		bean.setLstLocation(lstLocation);
		bean.setLstMealTime(lstMealTime);
		bean.setLstMealType(lstMealType);
		bean.setLstShift(lstShift);
		bean.setListMealOder(lstOrder);
		return bean;
	}

	@Override
	public Boolean saveCatering(String userRole, List<MealsOrderBean> listMealOder, String date) {
		Date cateringDate = FunctionUtils.convertDateByFormatLocal(date, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date cateringTime = new Date();
		if (listMealOder != null && !listMealOder.isEmpty()) {
			for (MealsOrderBean obj : listMealOder) {
				Catering dto = cateringDAO.getByStaffId(obj.getStaffId(), cateringDate);
				if (dto == null) {
					dto = new Catering();
					dto.setStaffId(obj.getStaffId());
				}
				Staff tsStaff = staffDAO.getByStaff(obj.getStaffId());
				if (tsStaff != null) {
					dto.setDepartId(tsStaff.getDeptId());
				}
				dto.setMealId(obj.getMealId());
				dto.setMealTimeId(obj.getMealTimeId());
				dto.setLocationId(obj.getLocationId());
				dto.setShiftId(obj.getShiftId());
				dto.setCateringDate(cateringDate);
				dto.setCateringTime(cateringTime);
				if (userRole != null && userRole.equals(ConstanKey.ROLE.ROLE_ADMIN)) {
					dto.setCatered(true);
					dto.setStatus(true);
					dto.setOrdered(true);
				} else if (userRole != null && userRole.equals(ConstanKey.ROLE.ROLE_MANAGER)) {
					dto.setCatered(false);
					dto.setStatus(true);
					dto.setOrdered(true);
				} else {
					dto.setCatered(false);
					dto.setStatus(false);
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
		if (dateTime != null) {
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
		Date tomorrow = new Date(toTime.getTime() + (1000 * 60 * 60 * 24));
		if (fromTime != null && toTime != null) {
			List<Catering> lst = cateringDAO.getLstByDate(fromTime, tomorrow);
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
	public Boolean saveStaff(StaffBean bean) {
		if (bean != null) {
			Staff staff = new Staff();
			staff.setStaffId(bean.getStaffId());
			staff.setStaffName(bean.getStaffName());
			staff.setAddress(bean.getAddress());
			staff.setCardId(bean.getCardId());
			staff.setDeptId(bean.getDepartId());
			staff.setDisable(false);
			staff.setEmail(bean.getEmail());
			staff.setGender(bean.getGender());
			staff.setJobTitle(bean.getJobTitle());
			staff.setPhoneNum(bean.getPhoneNum());
			return staffDAO.saveStaff(staff);
		}
		return null;
	}

	@Override
	public Boolean saveUser(UserBean bean) {
		if (bean != null) {
			User user = new User();
			user.setStaffId(bean.getStaffId());
			user.setDisable(false);
			user.setPassword(bean.getPassword());
			user.setStaffId(bean.getStaffId());
			if (bean.getUserId() != null) {
				user.setUserId(bean.getUserId());
			}
			user.setUserName(bean.getUserName());
			user.setUserRoleId(bean.getUserRoleId());
			return userDAO.saveUser(user);
		}
		return null;
	}

	@Override
	public List<RoleBean> getAllRole() {
		List<RoleBean> lstAll = new ArrayList<>();
		List<UserRole> lst = constantDAO.getAllRole();
		if (lst != null) {
			for (UserRole obj : lst) {
				RoleBean bean = new RoleBean();
				bean.setUserRoleID(obj.getUserRoleID());
				bean.setName(obj.getName());
				lstAll.add(bean);
			}
		}
		return lstAll;
	}

	@Override
	public DataExportBean getDataExport(String fromDate, String toDate) {
		DataExportBean bean = new DataExportBean();
		Date fromTime = FunctionUtils.convertDateByFormatLocal(fromDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date toTime = FunctionUtils.convertDateByFormatLocal(toDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date tomorrow = new Date(toTime.getTime() + (1000 * 60 * 60 * 24));
		if (fromTime != null && toTime != null) {
			Map<Integer, List<MealsOrderBean>> mapDataByDepart = new HashMap<>();
			Map<Integer, String> mapDepartMent = new HashMap<>();
			Map<Integer, String> mapLocation = new HashMap<>();
			List<Catering> lst = cateringDAO.getLstByDate(fromTime, tomorrow);
			if (lst != null && !lst.isEmpty()) {
				for (Catering catering : lst) {
					Staff obj = staffDAO.getByStaff(catering.getStaffId());
					if (obj != null) {
						List<MealsOrderBean> list = mapDataByDepart.get(obj.getDeptId());
						if (list == null) {
							list = new ArrayList<>();
						}
						if (!mapDepartMent.containsKey(catering.getDepartId())) {
							Department department = constantDAO.getDepartment(catering.getDepartId());
							mapDepartMent.put(department.getDeptId(), department.getDeptName());
						}
						if (!mapLocation.containsKey(catering.getLocationId())) {
							Location location = constantDAO.getLocationById(catering.getLocationId());
							mapLocation.put(location.getLocationId(), location.getLocationName());
						}
						MealsOrderBean oderBean = new MealsOrderBean();
						oderBean.setStaffId(obj.getStaffId());
						oderBean.setStaffName(obj.getStaffName());
						oderBean.setDepartmentId(catering.getDepartId());
						oderBean.setMealTimeId(catering.getMealTimeId());
						oderBean.setMealId(catering.getMealId());
						oderBean.setLocationId(catering.getLocationId());
						oderBean.setShiftId(catering.getShiftId());
						list.add(oderBean);
						mapDataByDepart.put(catering.getDepartId(), list);
					}
				}
			}
			String startDate = FunctionUtils.convertDateStringByFormatLocal(fromTime,
					ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT);
			String endDate = FunctionUtils.convertDateStringByFormatLocal(toTime,
					ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT);
			bean.setFromDate(startDate);
			bean.setToDate(endDate);
			bean.setMapDataByDepart(mapDataByDepart);
			bean.setMapDepartMent(mapDepartMent);
			bean.setMapLocation(mapLocation);
		}
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
