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
import com.meals.frontend.bean.DataCateringExport;
import com.meals.frontend.bean.StaffBean;
import com.meals.frontend.service.MealsService;
import com.meals.frontend.until.ConstanKey;

@Controller
@RequestMapping("exportData")
public class ExportContrller {
	@Autowired
	MealsService mealsService;

	@RequestMapping(value = "/exportCanteen", method = RequestMethod.GET)
	public ModelAndView exportCanteen(HttpSession session, @RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate) {
		ModelAndView model = new ModelAndView("excelView");
		List<CanteenExportBean> lst = mealsService.getLstAndCount(fromDate, toDate);
		String userRole = (String) session.getAttribute(ConstanKey.USER_ROLE);
		model.addObject("dataCanteen", lst);
		model.addObject("fromDate", fromDate);
		model.addObject("toDate", toDate);
		model.addObject(ConstanKey.USER_ROLE, userRole);
		return model;
	}

	@RequestMapping(value = "/exportManager", method = RequestMethod.GET)
	public ModelAndView exportManager(HttpSession session, String staffId,
			@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate) {
		ModelAndView model = new ModelAndView("excelView");
		Integer userId = (Integer) session.getAttribute(ConstanKey.USER_ID);
		String userRole = (String) session.getAttribute(ConstanKey.USER_ROLE);
		StaffBean staffBean = mealsService.getStaffByUserId(userId);
		List<DataCateringExport> lst = null;
		if (staffBean != null) {
			lst = mealsService.getDataExportByRole(userRole, staffBean.getDepartId(), staffId, fromDate, toDate);
		}
		model.addObject("dataManager", lst);
		model.addObject("fromDate", fromDate);
		model.addObject("toDate", toDate);
		model.addObject(ConstanKey.USER_ROLE, userRole);
		return model;
	}

	@RequestMapping(value = "/exportAdmin", method = RequestMethod.GET)
	public ModelAndView exportAdmin(HttpSession session, Integer deptId, String staffId,
			@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate) {
		Integer userId = (Integer) session.getAttribute(ConstanKey.USER_ID);
		String userRole = (String) session.getAttribute(ConstanKey.USER_ROLE);
		ModelAndView model = new ModelAndView("excelView");
		List<DataCateringExport> lst = mealsService.getDataExportByRole(userRole, deptId, staffId, fromDate, toDate);
		model.addObject("dataAdmin", lst);
		model.addObject("fromDate", fromDate);
		model.addObject("toDate", toDate);
		model.addObject(ConstanKey.USER_ROLE, userRole);
		return model;
	}
}
