package com.nlbdistribution.controller;


import com.nlbdistribution.dao.WinningnumberDao;
import com.nlbdistribution.entity.Winningnumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WinningnumberController {

    @Autowired
    private WinningnumberDao dao;

    @RequestMapping(value = "/winningnumbers/list", method = RequestMethod.GET, produces = "application/json")
    public List<Winningnumber> winningnumber() {
        return dao.list();
    }


}
