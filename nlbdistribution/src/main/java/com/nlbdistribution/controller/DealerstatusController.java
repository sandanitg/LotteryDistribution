package com.nlbdistribution.controller;

import com.nlbdistribution.dao.DealerstatusDao;
import com.nlbdistribution.entity.Dealerstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DealerstatusController {

    @Autowired
    private DealerstatusDao dao;

    @RequestMapping(value = "/dealerstatuses/list", method = RequestMethod.GET, produces = "application/json")
    public List<Dealerstatus> dealerstatuses() {
        return dao.list();
    }


}

