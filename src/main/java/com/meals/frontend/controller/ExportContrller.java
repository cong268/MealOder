package com.meals.frontend.controller;

import java.util.Date;
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
import com.meals.frontend.bean.DepartmentBean;
import com.meals.frontend.bean.StaffBean;
import com.meals.frontend.service.MealsService;
import com.meals.frontend.until.ConstanKey;
import com.meals.frontend.until.FunctionUtils;

@Controller
@RequestMapping("exportData")
public class ExportContrller {
	@Autowired
	MealsService mealsService;

	@RequestMapping(value = "/exportCanteen", method = RequestMethod.GET)
	public ModelAndView exportCanteen(HttpSession session, @RequestParam(value = "fromDate") String fromDate,
			@RequestParam(value = "toDate") String toDate) {
		ModelAndView model = new ModelAndView("excelView");
		String userRole = (String) session.getAttribute(ConstanKey.USER_ROLE);
		Date startDate = FunctionUtils.convertDateByFormatLocal(fromDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date endDate = FunctionUtils.convertDateByFormatLocal(toDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		if (startDate != null && endDate != null) {
			List<CanteenExportBean> lst = mealsService.getLstAndCount(startDate, endDate);
			String fromTime = FunctionUtils.convertDateStringByFormatLocal(startDate,
					ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT);
			String toTime = FunctionUtils.convertDateStringByFormatLocal(endDate,
					ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT);
			model.addObject("dataCanteen", lst);
			model.addObject("fromDate", fromTime);
			model.addObject("toDate", toTime);
		}
		model.addObject(ConstanKey.USER_ROLE, userRole);
		return model;
	}

	@RequestMapping(value = "/exportManager", method = RequestMethod.GET)
	public ModelAndView exportManager(HttpSession session, String staffId,
			@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate) {
		ModelAndView model = new ModelAndView("excelView");
		Integer userId = (Integer) session.getAttribute(ConstanKey.USER_ID);
		String userRole = (String) session.getAttribute(ConstanKey.USER_ROLE);
		String userName = (String) session.getAttribute(ConstanKey.USER_NAME);
		List<DataCateringExport> lst = null;
		Date startDate = FunctionUtils.convertDateByFormatLocal(fromDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date endDate = FunctionUtils.convertDateByFormatLocal(toDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		if (startDate != null && endDate != null) {
			StaffBean staffBean = mealsService.getStaffByUserId(userId);
			if (staffBean != null) {
				lst = mealsService.getDataExportByRole(userRole, staffBean.getDepartId(), staffId, startDate, endDate);
			}
			String departName = mealsService.getDepartNameById(staffBean.getDepartId());
			String fromTime = FunctionUtils.convertDateStringByFormatLocal(startDate,
					ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT);
			String toTime = FunctionUtils.convertDateStringByFormatLocal(endDate,
					ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT);
			model.addObject("dataManager", lst);
			model.addObject("fromDate", fromTime);
			model.addObject("toDate", toTime);
			model.addObject("userName", userName);
			model.addObject("departName", departName);
		}
		model.addObject(ConstanKey.USER_ROLE, userRole);
		return model;
	}

	@RequestMapping(value = "/exportAdmin", method = RequestMethod.GET)
	public ModelAndView exportAdmin(HttpSession session, Integer deptId, String staffId,
			@RequestParam(value = "fromDate") String fromDate, @RequestParam(value = "toDate") String toDate) {
		Integer userId = (Integer) session.getAttribute(ConstanKey.USER_ID);
		String userRole = (String) session.getAttribute(ConstanKey.USER_ROLE);
		String userName = (String) session.getAttribute(ConstanKey.USER_NAME);
		ModelAndView model = new ModelAndView("excelView");
		Date startDate = FunctionUtils.convertDateByFormatLocal(fromDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		Date endDate = FunctionUtils.convertDateByFormatLocal(toDate, ConstanKey.FORMAT_DATE.DATE_TIME_FORMAT);
		if (startDate != null && endDate != null) {
			List<DataCateringExport> lst = mealsService.getDataExportByRole(userRole, deptId, staffId, startDate,
					endDate);
			String fromTime = FunctionUtils.convertDateStringByFormatLocal(startDate,
					ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT);
			String toTime = FunctionUtils.convertDateStringByFormatLocal(endDate,
					ConstanKey.FORMAT_DATE.DATE_SLASH_FORMAT);
			StaffBean staffBean = mealsService.getStaffByUserId(userId);
			String departName = mealsService.getDepartNameById(staffBean.getDepartId());
			model.addObject("dataAdmin", lst);
			model.addObject("fromDate", fromTime);
			model.addObject("toDate", toTime);
			model.addObject("userName", userName);
			model.addObject("departName", departName);
		}
		model.addObject(ConstanKey.USER_ROLE, userRole);
		return model;
	}
}
