package com.nlbdistribution.controller;


import com.nlbdistribution.dao.SupervisorDao;
import com.nlbdistribution.entity.Supervisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SupervisorController {

    @Autowired
    private SupervisorDao dao;

    @RequestMapping(value = "/supervisors/list", method = RequestMethod.GET, produces = "application/json")
    public List<Supervisor> supervisor() {
        return dao.list();
    }

}
