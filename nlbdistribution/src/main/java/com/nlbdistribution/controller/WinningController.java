package com.nlbdistribution.controller;

import com.nlbdistribution.dao.WinningDao;
import com.nlbdistribution.entity.Winning;
import com.nlbdistribution.entity.Winningnumber;
import com.nlbdistribution.util.ModuleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class WinningController {

    @Autowired
    private WinningDao dao;

    @RequestMapping(value = "/winnings", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Winning> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.WINNING,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }

    @RequestMapping(value = "/winnings", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Winning winning) {


        List<Winning> lotteries = dao.findAll();
        Stream<Winning> lotterystream = lotteries.stream();

        lotterystream = lotterystream.filter(e -> e.getDrawId().getId() == winning.getDrawId().getId());


        List<Winning> lotteries2 = lotterystream.collect(Collectors.toList());

        if (lotteries2.size() != 0) {
            return "Alreday Added";
        } else {


            for (Winningnumber e : winning.getWinningnumberList())
                e.setWinningId(winning);

            dao.save(winning);
            return "0";

        }
    }

    @RequestMapping(value = "/winnings", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Winning winning) {
        // if(AuthProvider.isAuthorized(username,password,"Invoice",AuthProvider.UPDATE)) {
        try {

            Winning winningfrompersistent = dao.getOne(winning.getId());
            winningfrompersistent.getWinningnumberList().clear();

            for(Winningnumber p : winning.getWinningnumberList()) {
                winningfrompersistent.getWinningnumberList().add(p);
                p.setWinningId(winningfrompersistent);
            }

            dao.save(winningfrompersistent);
            return "0";
        }
        catch(Exception e) {
            return "Error-Saving : " + e.getMessage();
        }
    }

    @RequestMapping(value = "/winnings", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Winning winning ) {

        //System.out.println("delete");

        if (AuthProvider.isAuthorized(username, password, ModuleList.WINNING, AuthProvider.DELETE)) {
            try {
                Winning w = dao.getOne(winning.getId());
                w.getWinningnumberList().remove(w);
                dao.save(w);
                dao.delete(w);
                return "0";

            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }

        else
            return "Error-Deleting : You have no Permission";


    }


}
