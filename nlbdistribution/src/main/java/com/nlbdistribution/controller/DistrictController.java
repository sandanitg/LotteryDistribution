package com.nlbdistribution.controller;

import com.nlbdistribution.dao.DistrictDao;
import com.nlbdistribution.entity.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DistrictController {

    @Autowired
    private DistrictDao dao;

    @RequestMapping(value = "/districts/list", method = RequestMethod.GET, produces = "application/json")
    public List<District> District() {
        return dao.list();
    }


}
