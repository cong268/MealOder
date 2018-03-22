package com.meals.frontend.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import com.meals.backend.model.CateringId;
import com.meals.backend.model.Department;
import com.meals.backend.model.Location;
import com.meals.backend.model.Meal;
import com.meals.backend.model.MealTime;
import com.meals.backend.model.Staff;
import com.meals.backend.model.UserRole;
import com.meals.backend.model.Users;
import com.meals.frontend.bean.CanteenExportBean;
import com.meals.frontend.bean.DataBean;
import com.meals.frontend.bean.DataCateringExport;
import com.meals.frontend.bean.DepartmentBean;
import com.meals.frontend.bean.ListObjectBean;
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
			bean.setAddress(staff.getAddress());
			bean.setCardId(staff.getCardId());
			bean.setDisable(false);
			bean.setEmail(staff.getEmail());
			bean.setGender(staff.getGender());
			bean.setPhoneNum(staff.getPhoneNum());
			bean.setAge(staff.getAge());
			bean.setNationality(staff.getNationality());
			bean.setPosition(staff.getPosition());
			bean.setProvice(staff.getProvice());
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
	public DataBean getLstOrderByDepart(Integer userId) {
		DataBean bean = new DataBean();
		List<LocationBean> lstLocation = getLocation();
		List<DepartmentBean> lstDepartMent = getDepartMent();
		List<MealTimeBean> lstMealTime = getAllMealTime();
		List<MealTypeBean> lstMealType = getAllMealType();
		List<MealsOrderBean> lstOrder = new ArrayList<>();
		Staff staff = staffDAO.getStaffByUserId(userId);
		if (staff != null) {
			List<Staff> lstStaff = staffDAO.getStaffByDepart(staff.getDeptId());
			if (lstStaff != null && !lstStaff.isEmpty()) {
				for (Staff obj : lstStaff) {
					if (!obj.getStaffId().equals(staff.getStaffId())) {
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
							orderBean.setMealTimeId(lstMealTime.get(0).getMealTimeId());
						}
						if (lstMealType != null && !lstMealType.isEmpty()) {
							orderBean.setMealId(lstMealType.get(0).getMealId());
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
				orderBean.setDepartmentId(staff.getDeptId());
			}
			if (lstLocation != null && !lstLocation.isEmpty()) {
				orderBean.setLocationId(lstLocation.get(0).getLocationId());
			}
			if (lstMealTime != null && !lstMealTime.isEmpty()) {
				orderBean.setMealTimeId(lstMealTime.get(0).getMealTimeId());
			}
			if (lstMealType != null && !lstMealType.isEmpty()) {
				orderBean.setMealId(lstMealType.get(0).getMealId());
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
		List<MealsOrderBean> lstOder = new ArrayList<>();
		Date dateTime = FunctionUtils.convertDateByFormatLocal(date, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Staff staff = staffDAO.getStaffByUserId(userId);
		if (staff != null) {
			List<Catering> lst = cateringDAO.getLstByOder(staff.getDeptId(), dateTime);
			if (lst != null && !lst.isEmpty()) {
				for (Catering catering : lst) {
					MealsOrderBean oderBean = converFromCatering(catering);
					Staff obj = staffDAO.getByStaff(catering.getId().getStaffId());
					if (obj != null) {
						oderBean.setStaffName(obj.getStaffName());
					} else {
						oderBean.setStaffName("Visitor");
					}
					lstOder.add(oderBean);
				}
			}
		}
		bean.setLstMealTime(getAllMealTime());
		bean.setLstMealType(getAllMealType());
		bean.setLstDepartMent(getDepartMent());
		bean.setLstLocation(getLocation());
		bean.setListMealOder(lstOder);
		return bean;
	}

	@Override
	public DataBean getLstByStatus(String date) {
		DataBean bean = new DataBean();
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
		bean.setLstMealTime(getAllMealTime());
		bean.setLstMealType(getAllMealType());
		bean.setLstDepartMent(getDepartMent());
		bean.setLstLocation(getLocation());
		bean.setListMealOder(lstOder);
		return bean;
	}

	@Override
	public DataBean getLstByDate(String fromDate, String toDate) {
		DataBean bean = new DataBean();
		List<MealsOrderBean> lstOder = new ArrayList<>();
		Date fromTime = FunctionUtils.convertDateByFormatLocal(fromDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date toTime = FunctionUtils.convertDateByFormatLocal(toDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		if (fromTime != null && toTime != null) {
			Date tomorrow = new Date(toTime.getTime() + (1000 * 60 * 60 * 24));
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
		bean.setLstMealTime(getAllMealTime());
		bean.setLstMealType(getAllMealType());
		bean.setLstDepartMent(getDepartMent());
		bean.setLstLocation(getLocation());
		bean.setListMealOder(lstOder);
		return bean;
	}

	@Override
	public String saveStaff(StaffBean bean, Boolean isNew) {
		if (bean != null) {
			if (isNew) {
				Staff obj = staffDAO.getByStaff(bean.getStaffId());
				if (obj != null) {
					return ConstanKey.EXITS;
				} else {
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
					staff.setAge(bean.getAge());
					staff.setNationality(bean.getNationality());
					staff.setPosition(bean.getPosition());
					staff.setProvice(bean.getProvice());
					if (staffDAO.saveStaff(staff)) {
						return ConstanKey.SUCCESS;
					} else {
						return ConstanKey.ERROR;
					}
				}
			} else {
				Staff staff = staffDAO.getByStaff(bean.getStaffId());
				staff.setStaffName(bean.getStaffName());
				staff.setAddress(bean.getAddress());
				staff.setCardId(bean.getCardId());
				staff.setDeptId(bean.getDepartId());
				staff.setEmail(bean.getEmail());
				staff.setGender(bean.getGender());
				staff.setJobTitle(bean.getJobTitle());
				staff.setPhoneNum(bean.getPhoneNum());
				staff.setAge(bean.getAge());
				staff.setNationality(bean.getNationality());
				staff.setPosition(bean.getPosition());
				staff.setProvice(bean.getProvice());
				if (staffDAO.saveStaff(staff)) {
					return ConstanKey.SUCCESS;
				} else {
					return ConstanKey.ERROR;
				}
			}
		}
		return null;
	}

	@Override
	public String saveUser(UserBean bean) {
		if (bean != null) {
			if (userDAO.checkExitsUser(bean.getUserName())) {
				return ConstanKey.EXITS;
			} else {
				Users user = new Users();
				user.setStaffId(bean.getStaffId());
				user.setDisable(false);
				user.setPassword(bean.getPassword());
				user.setStaffId(bean.getStaffId());
				user.setUserId(bean.getUserId());
				user.setUserName(bean.getUserName());
				user.setUserRoleId(bean.getUserRoleId());
				if (userDAO.saveUser(user)) {
					return ConstanKey.SUCCESS;
				} else {
					return ConstanKey.ERROR;
				}
			}
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
	public Boolean saveCateringByStaff(Integer userId, String userRole, List<MealsOrderBean> listMealOder,
			String fromDate, String toDate) {
		Date fromTime = FunctionUtils.convertDateByFormatLocal(fromDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date toTime = FunctionUtils.convertDateByFormatLocal(toDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Calendar cal = Calendar.getInstance();
		Date cateringTime = cal.getTime();
		if (fromTime != null && toTime != null) {
			if (listMealOder != null && !listMealOder.isEmpty()) {
				Staff tsStaff = staffDAO.getStaffByUserId(userId);
				if (tsStaff != null) {
					while (fromTime.compareTo(toTime) <= 0) {
						for (MealsOrderBean obj : listMealOder) {
							Catering catering = cateringDAO.getCateringById(obj.getStaffId(), fromTime,
									obj.getMealTimeId());
							Catering dto = new Catering();
							CateringId pk = new CateringId();
							pk.setStaffId(obj.getStaffId());
							pk.setMealTimeId(obj.getMealTimeId());
							pk.setCateringDate(fromTime);
							dto.setId(pk);
							dto.setDeptId(tsStaff.getDeptId());
							dto.setLocationId(obj.getLocationId());
							dto.setCateringTime(cateringTime);
							dto.setMealId(obj.getMealId());
							if (userRole != null && userRole.equals(ConstanKey.ROLE.ROLE_ADMIN)) {
								dto.setCatered(true);
								dto.setStatus(true);
								dto.setOrdered(true);
								dto.setDisable(false);
								cateringDAO.saveOrUpdate(dto);
							} else if (userRole != null && userRole.equals(ConstanKey.ROLE.ROLE_MANAGER)) {
								if (catering == null || !catering.isCatered()) {
									dto.setCatered(false);
									dto.setStatus(true);
									dto.setOrdered(true);
									dto.setDisable(false);
									cateringDAO.saveOrUpdate(dto);
								}
							} else {
								if (catering == null || !catering.isStatus()) {
									dto.setCatered(false);
									dto.setStatus(false);
									dto.setOrdered(true);
									dto.setDisable(false);
									cateringDAO.saveOrUpdate(dto);
								}
							}
						}
						cal.setTime(fromTime);
						cal.add(Calendar.DATE, 1);
						fromTime = cal.getTime();
					}
				}
			}
		}
		return true;
	}

	@Override
	public Boolean saveCateringByManager(ListObjectBean bean, String date, Integer departId) {
		Date cateringDate = FunctionUtils.convertDateByFormatLocal(date, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date cateringTime = new Date();
		if (bean != null) {
			List<MealsOrderBean> lstSave = bean.getLstCateringSave();
			List<MealsOrderBean> lstReject = bean.getLstCateringReject();
			if (lstReject != null && !lstReject.isEmpty()) {
				for (MealsOrderBean obj : lstReject) {
					cateringDAO.updateReject(obj.getStaffId(), cateringDate, obj.getMealTimeId());
				}
			}
			if (lstSave != null && !lstSave.isEmpty()) {
				List<Catering> lst = new ArrayList<>();
				for (MealsOrderBean obj : lstSave) {
					Catering dto = new Catering();
					CateringId pk = new CateringId();
					pk.setStaffId(obj.getStaffId());
					pk.setCateringDate(cateringDate);
					pk.setMealTimeId(obj.getMealTimeId());
					dto.setId(pk);
					dto.setDeptId(departId);
					dto.setLocationId(obj.getLocationId());
					dto.setCateringTime(cateringTime);
					dto.setMealId(obj.getMealId());
					dto.setCatered(false);
					dto.setStatus(true);
					dto.setOrdered(true);
					dto.setDisable(false);
					lst.add(dto);
				}
				cateringDAO.saveList(lst);
			}
		}
		return true;
	}

	@Override
	public Boolean saveCateringByAdmin(String date, ListObjectBean bean) {
		Date cateringDate = FunctionUtils.convertDateByFormatLocal(date, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		if (cateringDate != null) {
			List<MealsOrderBean> lstSave = bean.getLstCateringSave();
			List<MealsOrderBean> lstReject = bean.getLstCateringReject();
			if (lstReject != null && !lstReject.isEmpty()) {
				for (MealsOrderBean obj : lstReject) {
					cateringDAO.updateReject(obj.getStaffId(), cateringDate, obj.getMealTimeId());
				}
			}
			if (lstSave != null && !lstSave.isEmpty()) {
				for (MealsOrderBean obj : lstSave) {
					cateringDAO.updateCatered(obj.getStaffId(), cateringDate, obj.getMealTimeId());
				}
			}
		}
		return true;
	}

	@Override
	public List<CanteenExportBean> getLstAndCount(Date fromDate, Date toDate) {
		List<CanteenExportBean> result = new ArrayList<>();
		Map<Integer, String> mapDepart = getMapDepart();
		Map<Integer, String> mapMealType = getMapMealType();
		Map<Integer, String> mapMealTime = getMapMeaTime();
		Map<Integer, String> mapLocation = getMapLocation();
		Date tomorrow = new Date(toDate.getTime() + (1000 * 60 * 60 * 24));
		List<Object[]> lst = cateringDAO.getLstAndCount(fromDate, tomorrow);
		if (lst != null && !lst.isEmpty()) {
			for (int i = 0; i < lst.size(); i++) {
				Object[] row = (Object[]) lst.get(i);
				CanteenExportBean bean = new CanteenExportBean();
				Date date = (Date) row[0];
				bean.setDate(
						FunctionUtils.convertDateStringByFormatLocal(date, ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT));
				bean.setDepartment(mapDepart.get(row[1]));
				bean.setMealType(mapMealType.get(row[2]));
				bean.setMealTime(mapMealTime.get(row[3]));
				bean.setLocation(mapLocation.get(row[4]));
				bean.setQuantity((Long) row[5]);
				result.add(bean);
			}
		}
		return result;
	}

	@Override
	public List<MealsOrderBean> getHistoryStaff(Integer userId, String fromDate, String toDate) {
		List<MealsOrderBean> result = new ArrayList<>();
		Date fromTime = FunctionUtils.convertDateByFormatLocal(fromDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date toTime = FunctionUtils.convertDateByFormatLocal(toDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date currentDate = Calendar.getInstance().getTime();
		String timeStr = FunctionUtils.convertDateStringByFormatLocal(currentDate, ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT);
		if (fromTime != null && toTime != null) {
			Date tomorrow = new Date(toTime.getTime() + (1000 * 60 * 60 * 24));
			Staff staff = staffDAO.getStaffByUserId(userId);
			if (staff != null) {
				List<Catering> lst = cateringDAO.getHistoryStaff(staff.getStaffId(), fromTime, tomorrow);
				if (lst != null && !lst.isEmpty()) {
					for (Catering obj : lst) {
						MealsOrderBean bean = converFromCatering(obj);
						String timeCateringStr = FunctionUtils.convertDateStringByFormatLocal(obj.getId().getCateringDate(), ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT);
						if(timeStr.trim().equals(timeCateringStr.trim())){
							bean.setDisable(false);
						} else {
							if (obj.getId().getCateringDate().compareTo(currentDate) < 0) {
								bean.setDisable(true);
							} else {
								bean.setDisable(false);
							}
						}
						result.add(bean);
					}
				}
			}

		}
		return result;
	}

	@Override
	public List<DataCateringExport> getDataExportByRole(String userRole, Integer deptId, String staffId, Date fromTime,
			Date toTime) {
		List<DataCateringExport> result = new ArrayList<>();
		List<Catering> lst = null;
		Date tomorrow = new Date(toTime.getTime() + (1000 * 60 * 60 * 24));
		if (userRole.equals(ConstanKey.ROLE.ROLE_MANAGER)) {
			if (staffId != null && !staffId.equals("")) {
				lst = cateringDAO.getExportStaffByManager(staffId, fromTime, tomorrow);
			} else {
				lst = cateringDAO.getExportDepartByManager(deptId, fromTime, tomorrow);
			}
		} else if (userRole.equals(ConstanKey.ROLE.ROLE_ADMIN)) {
			if (staffId != null && !staffId.equals("")) {
				lst = cateringDAO.getExportStaffByAdmin(staffId, fromTime, tomorrow);
			} else if (deptId != null) {
				lst = cateringDAO.getExportDepartByAdmin(deptId, fromTime, tomorrow);
			} else {
				lst = cateringDAO.getLstByDate(fromTime, tomorrow);
			}
		}
		if (lst != null && !lst.isEmpty()) {
			for (Catering obj : lst) {
				DataCateringExport bean = convertToData(obj);
				result.add(bean);
			}
		}
		return result;
	}

	@Override
	public Boolean deleteCatering(MealsOrderBean bean) {
		if (bean != null) {
			Date date = FunctionUtils.convertDateByFormatLocal(bean.getDateMeal(),
					ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT);
			if (date != null) {
				return cateringDAO.deleteById(bean.getStaffId(), bean.getMealTimeId(), date);
			}
		}
		return false;
	}

	@Override
	public Boolean updateCatering(MealsOrderBean bean) {
		Date date = FunctionUtils.convertDateByFormatLocal(bean.getDateMeal(),
				ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT);
		if (date != null) {
			Catering catering = cateringDAO.getCateringById(bean.getStaffId(), date, bean.getMealTimeId());
			if (catering != null) {
				catering.setMealId(bean.getMealId());
				catering.setLocationId(bean.getLocationId());
				return cateringDAO.saveOrUpdate(catering);
			}
		}
		return false;
	}

	@Override
	public List<DepartmentBean> getAllDepart() {
		return getDepartMent();
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

	private MealsOrderBean converFromCatering(Catering obj) {
		MealsOrderBean bean = new MealsOrderBean();
		bean.setStaffId(obj.getId().getStaffId());
		bean.setDepartmentId(obj.getDeptId());
		bean.setCatered(obj.isCatered());
		bean.setStatus(obj.isStatus());
		bean.setOrdered(obj.isOrdered());
		bean.setLocationId(obj.getLocationId());
		bean.setMealTimeId(obj.getId().getMealTimeId());
		bean.setMealId(obj.getMealId());
		bean.setDateMeal(FunctionUtils.convertDateStringByFormatLocal(obj.getId().getCateringDate(),
				ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT));
		return bean;
	}

	private DataCateringExport convertToData(Catering obj) {
		Map<Integer, String> mapDepart = getMapDepart();
		Map<Integer, String> mapMealTime = getMapMeaTime();
		Map<Integer, String> mapLocation = getMapLocation();
		DataCateringExport bean = new DataCateringExport();
		bean.setStaffId(obj.getId().getStaffId());
		Staff staff = staffDAO.getByStaff(obj.getId().getStaffId());
		if (staff != null) {
			bean.setUserName(staff.getStaffName());
		} else {
			bean.setUserName("Visitor");
		}
		bean.setDate(FunctionUtils.convertDateStringByFormatLocal(obj.getId().getCateringDate(),
				ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT));
		bean.setDepartmentName(mapDepart.get(obj.getDeptId()));
		bean.setLocation(mapLocation.get(obj.getLocationId()));
		bean.setMealTime(mapMealTime.get(obj.getMealId()));
		bean.setQuantity(1L);
		return bean;
	}

	@Override
	public String changePassword(String userName, String passWord, String newPassword) {
		Users user = userDAO.getUser(userName, passWord);
		if (user != null) {
			user.setPassword(newPassword);
			if (userDAO.saveUser(user)) {
				return ConstanKey.SUCCESS;
			} else {
				return ConstanKey.ERROR;
			}
		}
		return ConstanKey.ERROR;
	}

	@Override
	public Boolean checkStaffId(String staffId) {
		Staff staff = staffDAO.getByStaff(staffId);
		if (staff == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public DataBean getLstShowManager(Integer userId, String fromDate, String toDate) {
		DataBean bean = new DataBean();
		List<MealsOrderBean> lstOder = new ArrayList<>();
		Date fromTime = FunctionUtils.convertDateByFormatLocal(fromDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date toTime = FunctionUtils.convertDateByFormatLocal(toDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		if (fromTime != null && toTime != null) {
			Staff staff = staffDAO.getStaffByUserId(userId);
			if (staff != null) {
				Date tomorrow = new Date(toTime.getTime() + (1000 * 60 * 60 * 24));
				List<Catering> lst = cateringDAO.getExportDepartByManager(staff.getDeptId(), fromTime, tomorrow);
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
						oderBean.setDateMeal(FunctionUtils.convertDateStringByFormatLocal(
								catering.getId().getCateringDate(), ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT));
						lstOder.add(oderBean);
					}
				}
			}
		}
		bean.setLstMealTime(getAllMealTime());
		bean.setLstMealType(getAllMealType());
		bean.setLstDepartMent(getDepartMent());
		bean.setLstLocation(getLocation());
		bean.setListMealOder(lstOder);
		return bean;
	}

	@Override
	public String getDepartNameById(Integer departId) {
		Map<Integer, String> mapDepart = getMapDepart();
		if (mapDepart != null && !mapDepart.isEmpty()) {
			return mapDepart.get(departId);
		}
		return null;
	}

	@Override
	public DataBean getLstApproOfStaff(String staffId, String date) {
		DataBean bean = new DataBean();
		List<MealsOrderBean> lstOder = new ArrayList<>();
		Date dateTime = FunctionUtils.convertDateByFormatLocal(date, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		if (dateTime != null){
			List<Catering> lst = cateringDAO.getLstApproOfStaff(staffId, dateTime);
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
					oderBean.setDateMeal(FunctionUtils.convertDateStringByFormatLocal(
							catering.getId().getCateringDate(), ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT));
					lstOder.add(oderBean);
				}
			}
		}
		bean.setLstMealTime(getAllMealTime());
		bean.setLstMealType(getAllMealType());
		bean.setLstDepartMent(getDepartMent());
		bean.setLstLocation(getLocation());
		bean.setListMealOder(lstOder);
		return bean;
	}
}
