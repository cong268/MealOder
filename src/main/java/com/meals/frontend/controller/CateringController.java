package com.meals.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.meals.frontend.bean.DataBean;
import com.meals.frontend.service.MealsService;

@RequestMapping("cateringController")
public class CateringController {
	@Autowired
	private MealsService mealsService;

	@RequestMapping(value = "/getMealByDepartment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public DataBean getMealByDepartment(@RequestParam Integer departId) {
		return mealsService.getLstOrderByDepart(departId);
	}

}
