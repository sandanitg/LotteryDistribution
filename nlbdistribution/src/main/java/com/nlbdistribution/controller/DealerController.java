package com.nlbdistribution.controller;

import com.nlbdistribution.dao.*;
import com.nlbdistribution.entity.Dealer;
import com.nlbdistribution.entity.Dealerstatus;
import com.nlbdistribution.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class DealerController {

    @Autowired
    private DealerDao dao;

    @Autowired
    private CityDao daoCity;

    @Autowired
    private DistrictDao daoDistrict;

    @Autowired
    private DealerstatusDao daoDealerstatus;

    @Autowired
    private GenderDao daoGender;

    @RequestMapping(value = "/dealers/list", method = RequestMethod.GET, produces = "application/json")
    public List<Dealer> dealer() {
        return dao.list();
    }

    @RequestMapping(value = "/dealers", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
        public Page<Dealer> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
            if(AuthProvider.isAuthorized(username,password, ModuleList.DEALER,AuthProvider.SELECT)) {
                return dao.findAll(PageRequest.of(page, size));
            }
        return null;
         }

    @RequestMapping(value = "/dealers", params = {"page", "size","fullname","districtid"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Dealer> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("fullname") String fullname,  @RequestParam("districtid") Integer districtid) {

        // System.out.println(fullname+"-"+districtid);


        if(AuthProvider.isAuthorized(username,password, ModuleList.DEALER,AuthProvider.SELECT)) {

            List<Dealer> dealers = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Dealer> dealerstream = dealers.stream();
            //dealerstream =dealerstream.filter(d -> !(d.getFullname().equals("Admin")));

            if (districtid != null)
                dealerstream = dealerstream.filter(d -> d.getCityId().getDistrictId().getName().equals(daoDistrict.getOne(districtid).getName()));
            dealerstream = dealerstream.filter(d -> d.getFullname().contains(fullname));

            List<Dealer> dealers2 = dealerstream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < dealers2.size() ? start + size : dealers2.size();
            Page<Dealer> dealpage = new PageImpl<>(dealers2.subList(start, end), PageRequest.of(page, size), dealers2.size());

            return dealpage;
        }

        return null;

    }


    @RequestMapping(value = "/dealers", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Dealer dealer) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.DEALER,AuthProvider.INSERT)) {
            Dealer dealnic = dao.findByNic(dealer.getNic());
            Dealer dealfullname = dao.findByFullname(dealer.getFullname());
            if (dealnic != null)
                return "Error-Validation : NIC Exists";
            else if (dealfullname != null)
                return "Error-Validation : Full Name Exists";
            else
                try {
                    dao.save(dealer);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/dealers", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Dealer dealer) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.DEALER,AuthProvider.UPDATE)) {
            Dealer deal = dao.findByFullname(dealer.getFullname());
            if(deal==null || dealer.getFullname()==dealer.getFullname()) {
                try {
                    dao.save(dealer);
                    return "0";
                }
                catch(Exception e) {
                    return "Error-Updating : "+e.getMessage();
                }
            }
            else {  return "Error-Updating : Name Exists"; }
        }
        return "Error-Updating : You have no Permission";
    }


}
