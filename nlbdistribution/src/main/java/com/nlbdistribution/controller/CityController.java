package com.nlbdistribution.controller;

import com.nlbdistribution.dao.CityDao;
import com.nlbdistribution.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    private CityDao dao;

    @RequestMapping(value ="/cities/list/bydistrict", params = {"districtid"}, method = RequestMethod.GET, produces = "application/json")
    public List<City> city(@CookieValue(value="username", required = false) String username,
                            @CookieValue(value="password") String password, Integer districtid){

        if(AuthProvider.isUser(username, password))
            return dao.listByDistrict(districtid);

        else return null;
    }

    /*@RequestMapping(value = "/cities/list", method = RequestMethod.GET, produces = "application/json")
    public List<City> city() {
        return dao.list();
    }*/

}


