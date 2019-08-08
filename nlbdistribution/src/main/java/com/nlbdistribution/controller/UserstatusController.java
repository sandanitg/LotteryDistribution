package com.nlbdistribution.controller;

import com.nlbdistribution.dao.UserstatusDao;
import com.nlbdistribution.entity.Userstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserstatusController {

    @Autowired
    private UserstatusDao dao;

    @RequestMapping(value = "/userstatuses/list", method = RequestMethod.GET, produces = "application/json")
    public List<Userstatus> gender() {
        return dao.list();
    }


}
