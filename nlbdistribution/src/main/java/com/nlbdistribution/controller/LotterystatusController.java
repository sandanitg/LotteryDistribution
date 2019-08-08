package com.nlbdistribution.controller;

import com.nlbdistribution.dao.LotterystatusDao;
import com.nlbdistribution.entity.Employeestatus;
import com.nlbdistribution.entity.Lotterystatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LotterystatusController {

    @Autowired
    private LotterystatusDao dao;

    @RequestMapping(value = "/lotterystatuses/list", method = RequestMethod.GET, produces = "application/json")
    public List<Lotterystatus> lotterystatuses() {
        return dao.list();
    }



}
