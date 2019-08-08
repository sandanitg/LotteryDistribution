package com.nlbdistribution.controller;


import com.nlbdistribution.dao.DigittypeDao;
import com.nlbdistribution.entity.Digittype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DigittypeController {

    @Autowired
    private DigittypeDao dao;

    @RequestMapping(value = "/digittypes/list", method = RequestMethod.GET, produces = "application/json")
    public List<Digittype> digittypes() {
        return dao.list();
    }



}


