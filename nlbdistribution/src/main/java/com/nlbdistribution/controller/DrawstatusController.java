package com.nlbdistribution.controller;

import com.nlbdistribution.dao.DrawstatusDao;
import com.nlbdistribution.entity.Drawstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DrawstatusController {

    @Autowired
    private DrawstatusDao dao;

    @RequestMapping(value = "/drawstatuses/list", method = RequestMethod.GET, produces = "application/json")
    public List<Drawstatus> drawstatus() {
        return dao.list();
    }

}
