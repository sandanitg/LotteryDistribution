package com.nlbdistribution.controller;


import com.nlbdistribution.dao.PrizeDao;
import com.nlbdistribution.entity.Prize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PrizeController {

    @Autowired
    private PrizeDao dao;

    @RequestMapping(value = "/prizes/list", method = RequestMethod.GET, produces = "application/json")
    public List<Prize> prizes() {
        return dao.list();
    }
}
