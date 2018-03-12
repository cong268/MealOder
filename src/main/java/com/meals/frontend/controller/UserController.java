package com.meals.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.meals.frontend.bean.UserBean;
import com.meals.frontend.service.MealsService;

@RestController
@RequestMapping("userController")
public class UserController {
	@Autowired
	private MealsService mealsService;
	@RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserBean getUser() {
		return mealsService.checkUserLogin("admin", "12345");
	}
}
