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
import com.meals.frontend.service.MealsService;
import com.meals.frontend.until.ConstanKey;

@RestController
@RequestMapping("cateringController")
public class CateringController {
	@Autowired
	private MealsService mealsService;

	@RequestMapping(value = "/getMealByStaff", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public DataBean getMealByDepartment(HttpSession session) {
		Integer userId = (Integer) session.getAttribute(ConstanKey.USER_ID);
		return mealsService.getMealByStaff(userId);
	}

	@RequestMapping(value = "/getMealByDepartment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public DataBean getMealByDepartment(@RequestParam Integer departId) {
		return mealsService.getLstOrderByDepart(departId);
	}
	
//	@RequestMapping(value = "/getMealAppro", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public DataBean getMealAppro(@RequestParam Integer userId) {
////		return mealsService.getLstOrderByDepart(userId);
//	}

	@RequestMapping(value = "/saveCatering", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean saveCatering(HttpSession session, @RequestBody List<MealsOrderBean> listMealOder,
			@RequestParam String date) {
		String userRole = (String) session.getAttribute(ConstanKey.USER_ROLE);
		return mealsService.saveCatering(userRole, listMealOder, date);
	}

}
