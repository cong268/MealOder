package com.meals.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ViewController {

    @RequestMapping("mealManagerment")
    public ModelAndView ListMealManagerment(){
        ModelAndView model = new ModelAndView("mealManagerment");
        return model;
    }

    @RequestMapping("approveMeal")
    public ModelAndView approveMeal(){
        ModelAndView model = new ModelAndView("approveMeal");
        return model;
    }

    @RequestMapping("registerUser")
    public ModelAndView registerUser(){
        ModelAndView model = new ModelAndView("registerUser");
        return model;
    }
}