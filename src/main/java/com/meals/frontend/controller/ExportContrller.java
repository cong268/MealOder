package com.meals.frontend.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.meals.frontend.bean.CanteenExportBean;
import com.meals.frontend.service.MealsService;

@Controller
@RequestMapping("exportData")
public class ExportContrller {
	@Autowired
	MealsService mealsService;

	@RequestMapping(value = "/exportCatering", method = RequestMethod.GET)
	public ModelAndView exportCatering(HttpSession session,@RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate) {
		ModelAndView model = new ModelAndView("excelView");
		List<CanteenExportBean> lst = mealsService.getLstAndCount(fromDate, toDate);
		model.addObject("dataExport", lst);
		model.addObject("fromDate", fromDate);
		model.addObject("toDate", toDate);
		return model;
	}
}
