package com.nlbdistribution.controller;


import com.nlbdistribution.dao.DealerDao;
import com.nlbdistribution.dao.DrawDao;
import com.nlbdistribution.dao.LotteryorderDao;
import com.nlbdistribution.dao.LotteryorderstatusDao;
import com.nlbdistribution.entity.Lotteryorder;
import com.nlbdistribution.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class LotteryorderController {


    @Autowired
    private LotteryorderDao dao;

    @Autowired
    private DrawDao daoDraw;

    @Autowired
    private DealerDao daoDealer;

    @Autowired
    private LotteryorderstatusDao daoLotteryorderstatus;

    @RequestMapping(value = "/lotteryorders", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Lotteryorder> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.LOTTERYORDER,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/lotteryorders", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Lotteryorder lotteryorder) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.LOTTERYORDER,AuthProvider.INSERT)) {

           Lotteryorder lotteryorderbydd = dao.findByDrawDealer(lotteryorder.getDrawId(),lotteryorder.getDealerId());

           lotteryorder.setDate(LocalDateTime.now());

            if (lotteryorderbydd != null)
                return "Error-Validation : Lottery Order Exists";
                try {
                    dao.save(lotteryorder);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }


    @RequestMapping(value = "/lotteryorders", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Lotteryorder lotteryorder) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.LOTTERYORDER,AuthProvider.UPDATE)) {
            Lotteryorder lo = dao.findByDrawDealer(lotteryorder.getDrawId(), lotteryorder.getDealerId());

            if(lo==null || lotteryorder.getDrawId()==lotteryorder.getDrawId() && lotteryorder.getDealerId()==lotteryorder.getDealerId()) {
                try {
                    dao.save(lotteryorder);
                    return "0";
                }
                catch(Exception e) {
                    return "Error-Updating : "+e.getMessage();
                }
            }
            else {  return "Error-Updating : Draw Exists for the Dealer"; }
        }
        return "Error-Updating : You have no Permission";
    }

    @RequestMapping(value = "/lotteryorders", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Lotteryorder lotteryorder ) {
        if(AuthProvider.isAuthorized(username,password,ModuleList.LOTTERYORDER,AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(lotteryorder.getId()));
                return "0";
            }
            catch(Exception e) {
                return "Error-Deleting : "+e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/lotteryorders", params = {"page", "size","quantity","dealerid","lotteryorderstatusid"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Lotteryorder> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("quantity") Integer quantity, @RequestParam("dealerid") Integer dealerid, @RequestParam("lotteryorderstatusid") Integer lotteryorderstatusid) {

        //System.out.println(quantity+dealerid+"-"+lotteryorderstatusid);

        if(AuthProvider.isAuthorized(username,password, ModuleList.LOTTERYORDER,AuthProvider.SELECT)) {

            List<Lotteryorder> lotteryorders = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Lotteryorder> lotteryorderstream = lotteryorders.stream();
            //employeestream = employeestream.filter(e -> !(e.getCallingname().equals("Admin")));

            if (dealerid != null)
                lotteryorderstream = lotteryorderstream.filter(l -> l.getDealerId().equals(daoDealer.getOne(dealerid)));
            if (lotteryorderstatusid != null)
                lotteryorderstream = lotteryorderstream.filter(l -> l.getLotteryorderstatusId().equals(daoLotteryorderstatus.getOne(lotteryorderstatusid)));
            lotteryorderstream = lotteryorderstream.filter(l -> l.getQuantity().equals(quantity));


            List<Lotteryorder> lotteryorders2 = lotteryorderstream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < lotteryorders2.size() ? start + size : lotteryorders2.size();
            Page<Lotteryorder> orderpage = new PageImpl<>(lotteryorders2.subList(start, end), PageRequest.of(page, size), lotteryorders2.size());

            return orderpage;
        }

        return null;

    }



}
