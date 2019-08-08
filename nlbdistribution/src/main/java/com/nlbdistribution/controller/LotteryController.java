package com.nlbdistribution.controller;


import com.nlbdistribution.dao.*;
import com.nlbdistribution.entity.Digittype;
import com.nlbdistribution.entity.Lottery;
import com.nlbdistribution.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class LotteryController {

    @Autowired private LotteryDao dao;

    @Autowired private DayDao daoDay;

    @Autowired private DigittypeDao daoDigittype;

    @Autowired private LotterytypeDao daoLotterytype;

    @Autowired private LotterycolorDao daoLotterycolor;

    @Autowired private LotterystatusDao daoLotterystatus;

    @RequestMapping(value = "/lotteries", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Lottery> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.LOTTERY,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/lotteries", params = {"page", "size","lotterytypeid","dayid"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Lottery> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("lotterytypeid") Integer lotterytypeid, @RequestParam("dayid") Integer dayid) {

         //System.out.println(lotterytypeid+"-"+dayid);


        if(AuthProvider.isAuthorized(username,password, ModuleList.LOTTERY,AuthProvider.SELECT)) {

            List<Lottery> lotteries = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Lottery> lotterystream = lotteries.stream();
           // lotterystream = lotterystream.filter(e -> !(e.getCallingname().equals("Admin")));

            if (lotterytypeid != null)
                lotterystream = lotterystream.filter(e -> e.getLotterytypeId().equals(daoLotterytype.getOne(lotterytypeid)));
            if (dayid != null)
                lotterystream = lotterystream.filter(e -> e.getDayId().equals(daoDay.getOne(dayid)));

             List<Lottery> lotteries2 = lotterystream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < lotteries2.size() ? start + size : lotteries2.size();
            Page<Lottery> lotpage = new PageImpl<>(lotteries2.subList(start, end), PageRequest.of(page, size), lotteries2.size());

            return lotpage;
        }

        return null;

    }

    @RequestMapping(value = "/lotteries", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password, @Validated @RequestBody Lottery lottery) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.LOTTERY,AuthProvider.INSERT)) {
            Lottery lotteryname = dao.findByName(lottery.getName());

            if (lotteryname != null)
                return "Error-Validation : Lottery Name Exists";
            else
                try {
                    dao.save(lottery);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Saving : You have no Permission";

    }

    @RequestMapping(value = "/lotteries", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Lottery lottery) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.LOTTERY,AuthProvider.UPDATE)) {
            Lottery lot = dao.findByName(lottery.getName());
            if(lot==null || lottery.getName()==lottery.getName()) {
                try {
                    dao.save(lottery);
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

    @RequestMapping(value = "/lotteries", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Lottery lottery ) {
        if(AuthProvider.isAuthorized(username,password,ModuleList.LOTTERY,AuthProvider.DELETE)) {
            try {
                dao.delete(dao.getOne(lottery.getId()));
                return "0";
            }
            catch(Exception e) {
                return "Error-Deleting : "+e.getMessage();
            }
        }
        return "Error-Deleting : You have no Permission";

    }

    @RequestMapping(value = "/lotteries/list", method = RequestMethod.GET, produces = "application/json")
    public List<Lottery> lottery() {
        return dao.list();
    }


    @RequestMapping(value ="/lotteries/list/bylotterytype", params = {"lotterytypeid"}, method = RequestMethod.GET, produces = "application/json")
    public List<Lottery> lottery(@CookieValue(value="username", required = false) String username,@CookieValue(value="password") String password,@Param("lotterytypeid") Integer lotterytypeid){

        if(AuthProvider.isUser(username, password))
            return dao.listByLotterytype(lotterytypeid);

        else return null;
    }

}
