package com.nlbdistribution.controller;


import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/ui")
public class UiController {



    @RequestMapping("/config")
    public ModelAndView config(){
        ModelAndView model = new ModelAndView();
        model.setViewName("config.html");
        return model;
    }

    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView model = new ModelAndView();
        model.setViewName("login.html");
        return model;
    }


    @RequestMapping("/mainwindow")
    public ModelAndView mainwindow(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("mainwindow.html",username,password);
    }


    @RequestMapping("/home")
    public ModelAndView home(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("home.html",username,password);
    }


    @RequestMapping("/employee")
    public ModelAndView employee(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("employee.html",username,password);
    }


    @RequestMapping("/user")
    public ModelAndView user(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("user.html",username,password);
    }

    @RequestMapping("/previlage")
    public ModelAndView previlage(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("previlage.html",username,password);

    }


    @RequestMapping("/changepassword")
    public ModelAndView changepassword(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("changepassword.html",username,password);
    }


    @RequestMapping("/designation")
    public ModelAndView designation(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("designation.html",username,password);
    }

    @RequestMapping("/lottery")
    public ModelAndView lottery(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("lottery.html",username,password);
    }

    @RequestMapping("/dealer")
    public ModelAndView dealer(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("dealer.html",username,password);
    }

    @RequestMapping("/draw")
    public ModelAndView draw(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("draw.html",username,password);
    }

    @RequestMapping("/lotteryorder")
    public ModelAndView lotteryorder(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("lotteryorder.html",username,password);
    }

    @RequestMapping("/lotterytype")
    public ModelAndView lotterytype(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("lotterytype.html",username,password);
    }

    @RequestMapping("/winning")
    public ModelAndView winning(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("winning.html",username,password);
    }

    @RequestMapping("/lotterytypex")
    public ModelAndView lotterytypex(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("lotterytypex.html",username,password);
    }


    @RequestMapping("/dorder")
    public ModelAndView dorder(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("dorder.html",username,password);
    }

    @RequestMapping("/loginx")
    public ModelAndView loginx(@CookieValue(value="username") String username, @CookieValue(value="password") String password){
        return getModelView("loginx.html",username,password);
    }


    public ModelAndView getModelView(String page,String username, String password){

        ModelAndView model = new ModelAndView();

        if(AuthProvider.isUser(username,password)) {

            model.setViewName(page);
        }
        else {
            model.setViewName("noprivilage.html");

        }
        return model;

    }


}