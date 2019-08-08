package com.nlbdistribution.controller;


import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/ui/report")
public class ReportUiController {

    @RequestMapping("/systemaccessanalysis")
    public ModelAndView login(){
        ModelAndView model = new ModelAndView();
        model.setViewName("systemaccessanalysis.html");
        return model;
    }

    @RequestMapping("/drawanalysis")
    public ModelAndView drawss(){
        ModelAndView model = new ModelAndView();
        model.setViewName("drawanalysis.html");
        return model;
    }


    @RequestMapping("/dealeranalysis")
    public ModelAndView dealerx(){
        ModelAndView model = new ModelAndView();
        model.setViewName("dealeranalysis.html");
        return model;
    }

}