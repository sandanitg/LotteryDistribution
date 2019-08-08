package com.nlbdistribution.controller;

import com.nlbdistribution.dao.LotterytypedigitoptionDao;
import com.nlbdistribution.entity.Lotterytypedigitoption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LotterytypedigitoptionController {

    @Autowired
    private LotterytypedigitoptionDao dao;


    /*@RequestMapping(value = "/lotterytypedigitoptions/list", method = RequestMethod.GET, produces = "application/json")
    public List<Lotterytypedigitoption> lotterytypedigitoption() {
        return dao.list();
    }*/

    @RequestMapping(value ="/lotterytypedigitoptions/list/bylotterytype", params = {"lotterytypeid"}, method = RequestMethod.GET, produces = "application/json")
    public List<Lotterytypedigitoption> lotterytypedigitoption(@CookieValue(value="username", required = false) String username,
                                             @CookieValue(value="password") String password, Integer lotterytypeid){

        if(AuthProvider.isUser(username, password))
            return dao.listByLotterytype(lotterytypeid);

        else return null;
    }

}
