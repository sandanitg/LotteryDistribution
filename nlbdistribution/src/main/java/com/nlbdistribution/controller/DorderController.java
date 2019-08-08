package com.nlbdistribution.controller;

import com.nlbdistribution.dao.DorderDao;
import com.nlbdistribution.entity.Dorder;
import com.nlbdistribution.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class DorderController {

    @Autowired
    private DorderDao dao;

    @RequestMapping(value = "/dorders", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Dorder> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.DORDER,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

}
