package com.meals.frontend.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meals.frontend.bean.DataBean;
import com.meals.frontend.bean.MealsOrderBean;
import com.meals.frontend.bean.StaffBean;
import com.meals.frontend.service.MealsService;
import com.meals.frontend.until.ConstanKey;

@RestController
@RequestMapping("cateringController")
public class CateringController {
	@Autowired
	private MealsService mealsService;

	// Service cho Nhan Vien dang y
	@RequestMapping(value = "/getMealByStaff", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public DataBean getMealByDepartment(HttpSession session) {
		Integer userId = (Integer) session.getAttribute(ConstanKey.USER_ID);
		return mealsService.getMealByStaff(userId);
	}

	// Service cho Manager dang y
	@RequestMapping(value = "/getMealByDepartment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public DataBean getMealByDepartment(HttpSession session, @RequestParam(value = "date") String date) {
		Integer userId = (Integer) session.getAttribute(ConstanKey.USER_ID);
		if (userId != null) {
			return mealsService.getLstOrderByDepart(userId, date);
		}
		return null;
	}

	// Service cho Manager Appro
	@RequestMapping(value = "/getLstByOder", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public DataBean getLstByOder(HttpSession session, @RequestParam(value = "date") String date) {
		Integer userId = (Integer) session.getAttribute(ConstanKey.USER_ID);
		if (userId != null) {
			return mealsService.getLstByOder(userId, date);
		}
		return null;
	}

	// Service cho Admin Catered
	@RequestMapping(value = "/getLstByStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public DataBean getLstByStatus(@RequestParam(value = "date") String date) {
		return mealsService.getLstByStatus(date);
	}

	// Service cho Admin Export
	@RequestMapping(value = "/getLstByDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public DataBean getLstByDate(@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate) {
		return mealsService.getLstByDate(fromDate, toDate);
	}

	@RequestMapping(value = "/saveCateringEmployee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean saveCateringEmployee(@RequestBody List<MealsOrderBean> listMealOder,
			@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) {
		return mealsService.saveCateringEmployee(listMealOder, fromDate, toDate);
	}

	@RequestMapping(value = "/saveCateringByManager", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean saveCateringByManager(HttpSession session, @RequestBody List<MealsOrderBean> listMealOder,
			@RequestParam("date") String date) {
		Integer userId = (Integer) session.getAttribute(ConstanKey.USER_ID);
		StaffBean bean = mealsService.getStaffByUserId(userId);
		if (bean != null) {
			return mealsService.saveCateringByManager(bean.getDepartId(), listMealOder, date);
		}
		return false;
	}

	@RequestMapping(value = "/saveCateringByAdmin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean saveCateringByAdmin(@RequestBody List<MealsOrderBean> listMealOder,
			@RequestParam("date") String date) {
		return mealsService.saveCateringByAdmin(listMealOder, date);
	}

}
