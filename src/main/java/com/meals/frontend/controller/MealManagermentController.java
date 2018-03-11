package com.meals.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/private")
public class MealManagermentController {

    @RequestMapping("mealManagerment")
    public ModelAndView ListMealManagerment(){
        ModelAndView model = new ModelAndView("mealManagerment");
        return model;
    }
}
