package com.nlbdistribution.controller;

import com.nlbdistribution.dao.DorderstatusDao;
import com.nlbdistribution.entity.Dorderstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DorderstatusController {

    @Autowired
    private DorderstatusDao dao;

    @RequestMapping(value = "/dorderstatuses/list", method = RequestMethod.GET, produces = "application/json")
    public List<Dorderstatus> dorderstatus() {
        return dao.list();
    }



}
