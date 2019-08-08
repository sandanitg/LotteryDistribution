package com.nlbdistribution.controller;

import com.nlbdistribution.dao.DigitsizeDao;
import com.nlbdistribution.entity.Digitsize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DigitsizeController {

    @Autowired
    private DigitsizeDao dao;

    @RequestMapping(value = "/digitsizes/list", method = RequestMethod.GET, produces = "application/json")
    public List<Digitsize> digitsizes() {
        return dao.list();
    }

}
