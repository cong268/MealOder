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

    @RequestMapping("showDataManager")
    public ModelAndView showDataManager(){
        ModelAndView model = new ModelAndView("showDataManager");
        return model;
    }

    @RequestMapping("editStaffOrder")
    public ModelAndView editStaffOrder(){
        ModelAndView model = new ModelAndView("editStaffOrder");
        return model;
    }

//    @RequestMapping("approveMeal")
//    public ModelAndView approveMeal(){
//        ModelAndView model = new ModelAndView("approveMeal");
//        return model;
//    }

    @RequestMapping("userOrder")
    public ModelAndView userOrder(){
        ModelAndView model = new ModelAndView("userOrder");
        return model;
    }

    @RequestMapping("showDataUser")
    public ModelAndView showDataUser(){
        ModelAndView model = new ModelAndView("showDataUser");
        return model;
    }

    @RequestMapping("carterAdmin")
    public ModelAndView carteredAdmin(){
        ModelAndView model = new ModelAndView("carterAdmin");
        return model;
    }

    @RequestMapping("showDataAdmin")
    public ModelAndView showDataAdmin(){
        ModelAndView model = new ModelAndView("showDataAdmin");
        return model;
    }

    @RequestMapping("showDataChef")
    public ModelAndView showDataChef(){
        ModelAndView model = new ModelAndView("showDataChef");
        return model;
    }

    @RequestMapping("registerUser")
    public ModelAndView registerUser(){
        ModelAndView model = new ModelAndView("registerUser");
        return model;
    }

    @RequestMapping("registerStaff")
    public ModelAndView registerStaff(){
        ModelAndView model = new ModelAndView("registerStaff");
        return model;
    }
}
