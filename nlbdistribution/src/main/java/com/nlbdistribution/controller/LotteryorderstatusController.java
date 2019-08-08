package com.nlbdistribution.controller;

import com.nlbdistribution.dao.LotteryorderstatusDao;
import com.nlbdistribution.entity.Lotteryorderstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LotteryorderstatusController {

    @Autowired
    private LotteryorderstatusDao dao;

    @RequestMapping(value = "/lotteryorderstatuses/list", method = RequestMethod.GET, produces = "application/json")
    public List<Lotteryorderstatus> lotteryorderstatus() {
        return dao.list();
    }

}
