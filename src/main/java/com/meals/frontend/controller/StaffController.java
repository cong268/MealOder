package com.meals.frontend.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meals.frontend.bean.StaffBean;
import com.meals.frontend.service.MealsService;

@RestController
@RequestMapping("staffController")
public class StaffController {
	@Autowired
	private MealsService mealsService;

	@RequestMapping(value = "/getStaffById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public StaffBean getStaffById(HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		return mealsService.getStaffByUserId(userId);
	}
}
