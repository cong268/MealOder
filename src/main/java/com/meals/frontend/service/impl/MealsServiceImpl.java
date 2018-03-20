package com.meals.frontend.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meals.backend.dao.CateringDAO;
import com.meals.backend.dao.ConstantDAO;
import com.meals.backend.dao.StaffDAO;
import com.meals.backend.dao.UserDAO;
import com.meals.backend.model.Catering;
import com.meals.backend.model.CateringId;
import com.meals.backend.model.Department;
import com.meals.backend.model.Location;
import com.meals.backend.model.Meal;
import com.meals.backend.model.MealTime;
import com.meals.backend.model.Staff;
import com.meals.backend.model.Users;
import com.meals.backend.model.UserRole;
import com.meals.frontend.bean.CanteenExportBean;
import com.meals.frontend.bean.DataBean;
import com.meals.frontend.bean.DataExportBean;
import com.meals.frontend.bean.DepartmentBean;
import com.meals.frontend.bean.LocationBean;
import com.meals.frontend.bean.MealTimeBean;
import com.meals.frontend.bean.MealTypeBean;
import com.meals.frontend.bean.MealsOrderBean;
import com.meals.frontend.bean.RoleBean;
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

	@Override
	public StaffBean getStaffByUserId(Integer userId) {
		StaffBean bean = new StaffBean();
		Staff staff = staffDAO.getStaffByUserId(userId);
		if (staff != null) {
			bean.setStaffId(staff.getStaffId());
			bean.setStaffName(staff.getStaffName());
			bean.setJobTitle(staff.getJobTitle());
			Department depart = constantDAO.getDepartment(staff.getDeptId());
			bean.setDepartId(depart.getDeptId());
			bean.setDepartment(depart.getDeptName());
		}
		return bean;
	}

	@Override
	public UserBean checkUserLogin(String userName, String passWord) {
		UserBean bean = new UserBean();
		Users user = userDAO.getUser(userName, passWord);
		if (user != null) {
			bean.setUserId(user.getUserId());
			bean.setStaffId(user.getStaffId());
			bean.setUserName(user.getUserName());
			bean.setPassword(user.getPassword());
			bean.setDisable(user.isDisable());
			UserRole role = constantDAO.getRoleById(user.getUserRoleId());
			if (role != null) {
				bean.setUserRole(role.getName());
			}
			return bean;
		}
		return null;
	}

	@Override
	public DataBean getLstOrderByDepart(Integer userId, String date) {
		DataBean bean = new DataBean();
		List<LocationBean> lstLocation = getLocation();
		List<DepartmentBean> lstDepartMent = getDepartMent();
		List<MealTimeBean> lstMealTime = getAllMealTime();
		List<MealTypeBean> lstMealType = getAllMealType();
		Date dateTime = FunctionUtils.convertDateByFormatLocal(date, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		List<MealsOrderBean> lstOrder = new ArrayList<>();
		Staff staff = staffDAO.getStaffByUserId(userId);
		if (staff != null) {
			List<Staff> lstStaff = staffDAO.getStaffByDepart(staff.getDeptId());
			if (lstStaff != null && !lstStaff.isEmpty()) {
				for (Staff obj : lstStaff) {
					Catering catering = cateringDAO.getByStaffId(obj.getStaffId(), dateTime);
					if (catering == null || !catering.isOrdered()) {
						MealsOrderBean orderBean = new MealsOrderBean();
						orderBean.setStaffId(obj.getStaffId());
						orderBean.setStaffName(obj.getStaffName());
						if (lstDepartMent != null && !lstDepartMent.isEmpty()) {
							orderBean.setDepartmentId(lstDepartMent.get(0).getDeptId());
						}
						if (lstLocation != null && !lstLocation.isEmpty()) {
							orderBean.setLocationId(lstLocation.get(0).getLocationId());
						}
						if (lstMealTime != null && !lstMealTime.isEmpty()) {
							orderBean.setLocationId(lstMealTime.get(0).getMealTimeId());
						}
						if (lstMealType != null && !lstMealType.isEmpty()) {
							orderBean.setLocationId(lstMealType.get(0).getMealId());
						}
						lstOrder.add(orderBean);
					}
				}
			}
		}
		bean.setLstMealTime(lstMealTime);
		bean.setLstMealType(lstMealType);
		bean.setLstDepartMent(lstDepartMent);
		bean.setLstLocation(lstLocation);
		bean.setListMealOder(lstOrder);
		return bean;
	}

	@Override
	public DataBean getMealByStaff(Integer userId) {
		DataBean bean = new DataBean();
		List<LocationBean> lstLocation = getLocation();
		List<DepartmentBean> lstDepartMent = getDepartMent();
		List<MealTimeBean> lstMealTime = getAllMealTime();
		List<MealTypeBean> lstMealType = getAllMealType();
		MealsOrderBean orderBean = new MealsOrderBean();
		Staff staff = staffDAO.getStaffByUserId(userId);
		if (staff != null) {
			orderBean.setStaffId(staff.getStaffId());
			orderBean.setStaffName(staff.getStaffName());
			if (lstDepartMent != null && !lstDepartMent.isEmpty()) {
				orderBean.setDepartmentId(lstDepartMent.get(0).getDeptId());
			}
			if (lstLocation != null && !lstLocation.isEmpty()) {
				orderBean.setLocationId(lstLocation.get(0).getLocationId());
			}
			if (lstMealTime != null && !lstMealTime.isEmpty()) {
				orderBean.setLocationId(lstMealTime.get(0).getMealTimeId());
			}
			if (lstMealType != null && !lstMealType.isEmpty()) {
				orderBean.setLocationId(lstMealType.get(0).getMealId());
			}
		}
		bean.setLstDepartMent(lstDepartMent);
		bean.setLstLocation(lstLocation);
		bean.setLstMealTime(lstMealTime);
		bean.setLstMealType(lstMealType);
		bean.setListMealOder(Arrays.asList(orderBean));
		return bean;
	}

	@Override
	public DataBean getLstByOder(Integer userId, String date) {
		DataBean bean = new DataBean();
		List<LocationBean> lstLocation = getLocation();
		List<DepartmentBean> lstDepartMent = getDepartMent();
		List<MealTimeBean> lstMealTime = getAllMealTime();
		List<MealTypeBean> lstMealType = getAllMealType();
		List<MealsOrderBean> lstOder = new ArrayList<>();
		Date dateTime = FunctionUtils.convertDateByFormatLocal(date, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Staff staff = staffDAO.getStaffByUserId(userId);
		if (staff != null) {
			List<Catering> lst = cateringDAO.getLstByOder(staff.getDeptId(), dateTime);
			if (lst != null && !lst.isEmpty()) {
				for (Catering catering : lst) {
					MealsOrderBean oderBean = new MealsOrderBean();
					oderBean.setStaffId(catering.getId().getStaffId());
					Staff obj = staffDAO.getByStaff(catering.getId().getStaffId());
					if (obj != null) {
						oderBean.setStaffName(obj.getStaffName());
					} else {
						oderBean.setStaffName("Visitor");
					}
					oderBean.setDepartmentId(catering.getDeptId());
					oderBean.setLocationId(catering.getLocationId());
					oderBean.setMealId(catering.getMealId());
					oderBean.setMealTimeId(catering.getId().getMealTimeId());
					lstOder.add(oderBean);
				}
			}
		}
		bean.setLstMealTime(lstMealTime);
		bean.setLstMealType(lstMealType);
		bean.setListMealOder(lstOder);
		bean.setLstDepartMent(lstDepartMent);
		bean.setLstLocation(lstLocation);
		return bean;
	}

	@Override
	public DataBean getLstByStatus(String date) {
		DataBean bean = new DataBean();
		List<LocationBean> lstLocation = getLocation();
		List<DepartmentBean> lstDepartMent = getDepartMent();
		List<MealTimeBean> lstMealTime = getAllMealTime();
		List<MealTypeBean> lstMealType = getAllMealType();
		List<MealsOrderBean> lstOder = new ArrayList<>();
		Date dateTime = FunctionUtils.convertDateByFormatLocal(date, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		if (dateTime != null) {
			List<Catering> lst = cateringDAO.getLstByStatus(dateTime);
			if (lst != null && !lst.isEmpty()) {
				for (Catering catering : lst) {
					MealsOrderBean oderBean = new MealsOrderBean();
					Staff obj = staffDAO.getByStaff(catering.getId().getStaffId());
					if (obj != null) {
						oderBean.setStaffName(obj.getStaffName());
					} else {
						oderBean.setStaffName("Visitor");
					}
					oderBean.setStaffId(catering.getId().getStaffId());
					oderBean.setDepartmentId(catering.getDeptId());
					oderBean.setLocationId(catering.getLocationId());
					oderBean.setMealId(catering.getMealId());
					oderBean.setMealTimeId(catering.getId().getMealTimeId());
					lstOder.add(oderBean);
				}
			}
		}
		bean.setLstMealTime(lstMealTime);
		bean.setLstMealType(lstMealType);
		bean.setListMealOder(lstOder);
		bean.setLstDepartMent(lstDepartMent);
		bean.setLstLocation(lstLocation);
		return bean;
	}

	@Override
	public DataBean getLstByDate(String fromDate, String toDate) {
		DataBean bean = new DataBean();
		List<LocationBean> lstLocation = getLocation();
		List<DepartmentBean> lstDepartMent = getDepartMent();
		List<MealTimeBean> lstMealTime = getAllMealTime();
		List<MealTypeBean> lstMealType = getAllMealType();
		List<MealsOrderBean> lstOder = new ArrayList<>();
		Date fromTime = FunctionUtils.convertDateByFormatLocal(fromDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date toTime = FunctionUtils.convertDateByFormatLocal(toDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date tomorrow = new Date(toTime.getTime() + (1000 * 60 * 60 * 24));
		if (fromTime != null && toTime != null) {
			List<Catering> lst = cateringDAO.getLstByDate(fromTime, tomorrow);
			if (lst != null && !lst.isEmpty()) {
				for (Catering catering : lst) {
					MealsOrderBean oderBean = new MealsOrderBean();
					oderBean.setStaffId(catering.getId().getStaffId());
					Staff obj = staffDAO.getByStaff(catering.getId().getStaffId());
					if (obj != null) {
						oderBean.setStaffName(obj.getStaffName());
					} else {
						oderBean.setStaffName("Visitor");
					}
					oderBean.setDepartmentId(catering.getDeptId());
					oderBean.setLocationId(catering.getLocationId());
					oderBean.setMealId(catering.getMealId());
					oderBean.setMealTimeId(catering.getId().getMealTimeId());
					oderBean.setDateMeal(FunctionUtils.convertDateStringByFormatLocal(
							catering.getId().getCateringDate(), ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT));
					lstOder.add(oderBean);
				}
			}
		}
		bean.setLstMealTime(lstMealTime);
		bean.setLstMealType(lstMealType);
		bean.setListMealOder(lstOder);
		bean.setLstDepartMent(lstDepartMent);
		bean.setLstLocation(lstLocation);
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
			Users user = new Users();
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
				bean.setUserRoleID(obj.getUserRoleId());
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
			Map<Date, List<MealsOrderBean>> mapDataByDate = new TreeMap<>();
			List<Catering> lst = cateringDAO.getLstByDate(fromTime, tomorrow);
			if (lst != null && !lst.isEmpty()) {
				for (Catering catering : lst) {
					MealsOrderBean oderBean = new MealsOrderBean();
					Staff obj = staffDAO.getByStaff(catering.getId().getStaffId());
					if (obj != null) {
						oderBean.setStaffName(obj.getStaffName());
					} else {
						oderBean.setStaffName("Visitor");
					}
					List<MealsOrderBean> list = new ArrayList<>();
					if (mapDataByDate.containsKey(catering.getId().getCateringDate())) {
						list = mapDataByDate.get(catering.getId().getCateringDate());
					}
					oderBean.setStaffId(catering.getId().getStaffId());
					oderBean.setDepartmentId(catering.getDeptId());
					oderBean.setLocationId(catering.getLocationId());
					oderBean.setMealId(catering.getMealId());
					list.add(oderBean);
					mapDataByDate.put(catering.getId().getCateringDate(), list);
				}
			}
			String startDate = FunctionUtils.convertDateStringByFormatLocal(fromTime,
					ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT);
			String endDate = FunctionUtils.convertDateStringByFormatLocal(toTime,
					ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT);
			bean.setFromDate(startDate);
			bean.setToDate(endDate);
			bean.setLstMealType(getAllMealType());
			bean.setLstDepartMent(getDepartMent());
			bean.setLstLocation(getLocation());
			bean.setLstMealTime(getAllMealTime());
			bean.setMapDataByDate(mapDataByDate);
		}
		return bean;
	}

	@Override
	public Boolean saveCateringEmployee(List<MealsOrderBean> listMealOder, String fromDate, String toDate) {
		Date fromTime = FunctionUtils.convertDateByFormatLocal(fromDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date toTime = FunctionUtils.convertDateByFormatLocal(toDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Calendar cal = Calendar.getInstance();
		Date cateringTime = cal.getTime();
		if (fromTime != null && toTime != null) {
			if (listMealOder != null && !listMealOder.isEmpty()) {
				while (fromTime.compareTo(toTime) <= 0) {
					for (MealsOrderBean obj : listMealOder) {
						Catering dto = new Catering();
						CateringId pk = new CateringId();
						pk.setStaffId(obj.getStaffId());
						pk.setMealTimeId(obj.getMealTimeId());
						pk.setCateringDate(fromTime);
						Staff tsStaff = staffDAO.getByStaff(obj.getStaffId());
						if (tsStaff != null) {
							dto.setDeptId(tsStaff.getDeptId());
						}
						dto.setId(pk);
						dto.setLocationId(obj.getLocationId());
						dto.setCateringTime(cateringTime);
						dto.setMealId(obj.getMealId());
						dto.setCatered(false);
						dto.setStatus(false);
						dto.setOrdered(true);
						cateringDAO.saveOrUpdate(dto);
					}
					cal.setTime(fromTime);
					cal.add(Calendar.DATE, 1);
					fromTime = cal.getTime();
				}
			}
		}
		return true;
	}

	@Override
	public Boolean saveCateringByManager(Integer departId, List<MealsOrderBean> listMealOder, String date) {
		Date cateringDate = FunctionUtils.convertDateByFormatLocal(date, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date cateringTime = new Date();
		if (listMealOder != null && !listMealOder.isEmpty()) {
			for (MealsOrderBean obj : listMealOder) {
				Catering dto = new Catering();
				CateringId pk = new CateringId();
				pk.setStaffId(obj.getStaffId());
				pk.setCateringDate(cateringDate);
				pk.setMealTimeId(obj.getMealTimeId());
				dto.setDeptId(departId);
				dto.setLocationId(obj.getLocationId());
				dto.setCateringTime(cateringTime);
				dto.setMealId(obj.getMealId());
				dto.setCatered(false);
				dto.setStatus(true);
				dto.setOrdered(true);
				cateringDAO.saveOrUpdate(dto);
			}
		}
		return true;
	}

	@Override
	public Boolean saveCateringByAdmin(List<MealsOrderBean> listMealOder, String date) {
		Date cateringDate = FunctionUtils.convertDateByFormatLocal(date, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		if (listMealOder != null && !listMealOder.isEmpty()) {
			for (MealsOrderBean obj : listMealOder) {
				Catering dto = cateringDAO.getByStaffId(obj.getStaffId(), cateringDate);
				dto.setCatered(true);
				dto.setStatus(true);
				dto.setOrdered(true);
				cateringDAO.saveOrUpdate(dto);
			}
		}
		return true;
	}

	@Override
	public List<CanteenExportBean> getLstAndCount(String fromDate, String toDate) {
		List<CanteenExportBean> result = new ArrayList<>();
		Date fromTime = FunctionUtils.convertDateByFormatLocal(fromDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date toTime = FunctionUtils.convertDateByFormatLocal(toDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Map<Integer, String> mapDepart = getMapDepart();
		Map<Integer, String> mapMealType = getMapMealType();
		Map<Integer, String> mapMealTime = getMapMeaTime();
		Map<Integer, String> mapLocation = getMapLocation();
		if (fromTime != null && toTime != null) {
			Date tomorrow = new Date(toTime.getTime() + (1000 * 60 * 60 * 24));
			List<Object[]> lst = cateringDAO.getLstAndCount(fromTime, tomorrow);
			if (lst != null && !lst.isEmpty()) {
				for (int i = 0; i < lst.size(); i++) {
					Object[] row = (Object[]) lst.get(i);
					CanteenExportBean bean = new CanteenExportBean();
					Date date = (Date) row[0];
					bean.setDate(FunctionUtils.convertDateStringByFormatLocal(date,
							ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT));
					bean.setDepartment(mapDepart.get(row[1]));
					bean.setMealType(mapMealType.get(row[2]));
					bean.setMealTime(mapMealTime.get(row[3]));
					bean.setLocation(mapLocation.get(row[4]));
					bean.setQuantity((Integer) row[5]);
					result.add(bean);
				}
			}
		}
		return result;
	}

	private Map<Integer, String> getMapDepart() {
		Map<Integer, String> map = new HashMap<>();
		List<Department> lstDepart = constantDAO.getAllDepartment();
		if (lstDepart != null && !lstDepart.isEmpty()) {
			for (Department obj : lstDepart) {
				map.put(obj.getDeptId(), obj.getDeptName());
			}
		}
		return map;
	}

	private Map<Integer, String> getMapMealType() {
		Map<Integer, String> map = new HashMap<>();
		List<Meal> lst = constantDAO.getAllMeal();
		if (lst != null && !lst.isEmpty()) {
			for (Meal obj : lst) {
				map.put(obj.getMealId(), obj.getMealName());
			}
		}
		return map;
	}

	private Map<Integer, String> getMapMeaTime() {
		Map<Integer, String> map = new HashMap<>();
		List<MealTime> lst = constantDAO.getAllMealTime();
		if (lst != null && !lst.isEmpty()) {
			for (MealTime obj : lst) {
				map.put(obj.getMealTimeId(), obj.getMealTimeName());
			}
		}
		return map;
	}

	private Map<Integer, String> getMapLocation() {
		Map<Integer, String> map = new HashMap<>();
		List<Location> lstLocationDAO = constantDAO.getAllLocation();
		if (lstLocationDAO != null && !lstLocationDAO.isEmpty()) {
			for (Location obj : lstLocationDAO) {
				map.put(obj.getLocationId(), obj.getLocationName());
			}
		}
		return map;
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
		List<Department> lstDepart = constantDAO.getAllDepartment();
		if (lstDepart != null && !lstDepart.isEmpty()) {
			for (Department obj : lstDepart) {
				lstDepartMent.add(new DepartmentBean(obj.getDeptId(), obj.getDeptName()));
			}
		}
		return lstDepartMent;
	}

	private List<MealTimeBean> getAllMealTime() {
		List<MealTimeBean> list = new ArrayList<>();
		List<MealTime> lst = constantDAO.getAllMealTime();
		if (lst != null && !lst.isEmpty()) {
			for (MealTime obj : lst) {
				list.add(new MealTimeBean(obj.getMealTimeId(), obj.getMealTimeName(), obj.getDescription()));
			}
		}
		return list;
	}

	private List<MealTypeBean> getAllMealType() {
		List<MealTypeBean> list = new ArrayList<>();
		List<Meal> lst = constantDAO.getAllMeal();
		if (lst != null && !lst.isEmpty()) {
			for (Meal obj : lst) {
				list.add(new MealTypeBean(obj.getMealId(), obj.getMealName(), obj.getDescription()));
			}
		}
		return list;
	}
}
