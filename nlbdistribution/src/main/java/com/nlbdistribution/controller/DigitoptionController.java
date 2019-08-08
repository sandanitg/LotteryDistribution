package com.nlbdistribution.controller;


import com.nlbdistribution.dao.DigitoptionDao;
import com.nlbdistribution.entity.Digitoption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DigitoptionController {

    @Autowired
    private DigitoptionDao dao;

    @RequestMapping(value = "/digitoptions/list", method = RequestMethod.GET, produces = "application/json")
    public List<Digitoption> digitoptions() {
        return dao.list();
    }

    @RequestMapping(value ="/digitoptions/list/bylotterytype", params = {"lotterytypeid"}, method = RequestMethod.GET, produces = "application/json")
    public List<Digitoption> lottery(@CookieValue(value="username", required = false) String username, @CookieValue(value="password") String password, @Param("lotterytypeid") Integer lotterytypeid){

        if(AuthProvider.isUser(username, password))
            return dao.listByLottery(lotterytypeid);

        else return null;
    }
}
