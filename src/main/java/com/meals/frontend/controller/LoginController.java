package com.meals.frontend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.meals.frontend.bean.UserBean;
import com.meals.frontend.service.MealsService;
import com.meals.frontend.until.ConstanKey;

@Controller
@RequestMapping("/")
public class LoginController {
	@Autowired
	private MealsService mealsService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView showLoginPage(HttpServletRequest request) {
		request.getSession().removeAttribute(ConstanKey.USER_ID);
		request.getSession().removeAttribute(ConstanKey.USER_NAME);
		ModelAndView model = new ModelAndView("login");
		model.addObject("messsage", "");
		return model;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	protected ModelAndView logoutSession(HttpServletRequest request) {
		request.getSession().removeAttribute(ConstanKey.USER_ID);
		request.getSession().removeAttribute(ConstanKey.USER_NAME);
		ModelAndView model = new ModelAndView("login");
		return model;
	}

	@RequestMapping(value = "authentication", method = RequestMethod.POST)
    public ModelAndView doAuthentication(@RequestParam("username") String username,
								   @RequestParam("password") String password,
								 	HttpServletRequest request) {
		UserBean userBean = mealsService.checkUserLogin(username, password);
		if (userBean != null) {
			request.getSession().setAttribute(ConstanKey.USER_ID, userBean.getUserId());
			request.getSession().setAttribute(ConstanKey.USER_NAME, userBean.getUserName());
			request.getSession().setAttribute(ConstanKey.STAFF_ID, userBean.getStaffId());
			String userRole = userBean.getUserRole();
			if (userRole != null) {
				request.getSession().setAttribute(ConstanKey.USER_ROLE, userRole);
				if (userRole.equals(ConstanKey.ROLE.ROLE_ADMIN)) {
					// Admin, Quan ly nha bep
					ModelAndView model = new ModelAndView("redirect:mealManagerment");
					return model;
				} else if (userRole.equals(ConstanKey.ROLE.ROLE_MANAGER)) {
					// Truong phong
					ModelAndView model = new ModelAndView("redirect:mealManagerment");
					return model;
				} else {
					// Nhan vien
					ModelAndView model = new ModelAndView("redirect:mealManagerment");
					return model;
				}
			} else {
				// Nhan vien
				ModelAndView model = new ModelAndView("redirect:mealManagerment");
				return model;
			}
		} else {
			ModelAndView model = new ModelAndView("login");
			model.addObject("messsage", "Username or password is invalid!");
			return model;
		}
	}

}
