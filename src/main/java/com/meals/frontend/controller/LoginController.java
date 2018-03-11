package com.meals.frontend.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping("login")
    public ModelAndView showLoginPage(){
        ModelAndView model = new ModelAndView("login");
        model.addObject("messsage", "");
        return model;
    }

    @RequestMapping(value="authentication", method = RequestMethod.POST)
    public ModelAndView doAuthentication(@RequestParam("username") String username,
                                         @RequestParam("password") String password){
        if(username.equals("admin") == true &&
                password.equals("admin")){
            ModelAndView model = new ModelAndView("mealManagerment");
            return model;
        } else {
            ModelAndView model = new ModelAndView("login");
            model.addObject("messsage", "Username or password is invalid!");
            return model;
        }
    }

}
