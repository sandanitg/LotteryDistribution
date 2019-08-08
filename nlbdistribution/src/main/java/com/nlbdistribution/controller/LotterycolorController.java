package com.nlbdistribution.controller;

import com.nlbdistribution.dao.LotterycolorDao;
import com.nlbdistribution.entity.Lotterycolor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LotterycolorController {

    @Autowired
    private LotterycolorDao dao;

    @RequestMapping(value = "/lotterycolors/list", method = RequestMethod.GET, produces = "application/json")
    public List<Lotterycolor> lotterycolor() {
        return dao.list();
    }



}


