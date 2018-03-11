package com.meals.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.meals.frontend.bean.StaffBean;
import com.meals.frontend.service.MealsService;

@Controller
@RequestMapping("staffController")
public class StaffController {
	@Autowired
	private MealsService mealsService;
	
	@RequestMapping(value = "/getStaffById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public StaffBean getStaffById() {
		Integer userId = 1;
		return mealsService.getStaffByUserId(userId);
	}
}
