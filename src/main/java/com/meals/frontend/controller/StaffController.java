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

import com.meals.frontend.bean.DepartmentBean;
import com.meals.frontend.bean.RoleBean;
import com.meals.frontend.bean.StaffBean;
import com.meals.frontend.bean.UserBean;
import com.meals.frontend.service.MealsService;
import com.meals.frontend.until.ConstanKey;

@RestController
@RequestMapping("staffController")
public class StaffController {
	@Autowired
	private MealsService mealsService;

	@RequestMapping(value = "/getStaffById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public StaffBean getStaffById(HttpSession session) {
		Integer userId = (Integer) session.getAttribute(ConstanKey.USER_ID);
		return mealsService.getStaffByUserId(userId);
	}

	@RequestMapping(value = "/saveStaff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String saveStaff(HttpSession session, @RequestBody StaffBean bean,
			@RequestParam(value = "isNew") Boolean isNew) {
		String userRole = (String) session.getAttribute(ConstanKey.USER_ROLE);
		Integer userId = (Integer) session.getAttribute(ConstanKey.USER_ID);
		if (userRole.equals(ConstanKey.ROLE.ROLE_MANAGER)) {
			StaffBean staffBean = mealsService.getStaffByUserId(userId);
			bean.setDepartId(staffBean.getDepartId());
		}
		return mealsService.saveStaff(bean, isNew);
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String saveUser(HttpSession session, @RequestBody UserBean bean) {
		String userRole = (String) session.getAttribute(ConstanKey.USER_ROLE);
		List<RoleBean> lst = mealsService.getAllRole();
		if (userRole.equals(ConstanKey.ROLE.ROLE_MANAGER)) {
			if (lst != null && !lst.isEmpty()) {
				for (RoleBean obj : lst) {
					if (obj.getName().equals(ConstanKey.ROLE.ROLE_EMPLOYEE)) {
						bean.setUserRoleId(obj.getUserRoleID());
					}
				}
			}
		}
		return mealsService.saveUser(bean);
	}

	@RequestMapping(value = "/getAllRole", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RoleBean> getALlRole() {
		return mealsService.getAllRole();
	}

	@RequestMapping(value = "/getAllDepartment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DepartmentBean> getAllDepartment() {
		return mealsService.getAllDepart();
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String changePassword(@RequestParam String userName, @RequestParam String passWord,
			@RequestParam String newPassWord) {
		return mealsService.changePassword(userName, passWord, newPassWord);
	}

	@RequestMapping(value = "/checkStaffId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean checkStaffId(@RequestParam(value = "staffId") String staffId) {
		return mealsService.checkStaffId(staffId);
	}
}
