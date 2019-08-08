package com.nlbdistribution.controller;


import com.nlbdistribution.dao.LotterytypestatusDao;
import com.nlbdistribution.entity.Lotterytypestatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LotterytypestatusController {


    @Autowired
    private LotterytypestatusDao dao;

    @RequestMapping(value = "/lotterytypestatuses/list", method = RequestMethod.GET, produces = "application/json")
    public List<Lotterytypestatus> lotterytypestatuses() {
        return dao.list();
    }

}

