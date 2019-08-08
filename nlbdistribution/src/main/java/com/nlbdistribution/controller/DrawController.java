package com.nlbdistribution.controller;

import com.nlbdistribution.dao.*;
import com.nlbdistribution.entity.Draw;
import com.nlbdistribution.entity.Drawsupervisor;
import com.nlbdistribution.entity.Lotterytype;
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
public class DrawController {

    @Autowired
    private DrawDao dao;

    @Autowired
    private SupervisorDao daoSupervisor;

    @Autowired
    private DrawstatusDao daoDrawstatus;

    @Autowired
    private LotteryDao daoLottery;

    @RequestMapping(value = "/draws/list", method = RequestMethod.GET, produces = "application/json")
    public List<Draw> draw() { return dao.list();
    }

    @RequestMapping(value ="/draws/list/bylottery", params = {"lotterytypeid","dayid"}, method = RequestMethod.GET, produces = "application/json")
    public List<Draw> draw(@CookieValue(value="username", required = false) String username,
                           @CookieValue(value="password") String password, Integer lotterytypeid, Integer dayid){

        if(AuthProvider.isUser(username, password))
            return dao.listByLottery(lotterytypeid, dayid);

        else return null;
    }

    @RequestMapping(value ="/draws/list/bylotteryn", params = {"lotteryid"}, method = RequestMethod.GET, produces = "application/json")
    public List<Draw> draw(@CookieValue(value="username", required = false) String username,
                           @CookieValue(value="password") String password,@Param("lotteryid") Integer lotteryid){

        if(AuthProvider.isUser(username, password))
            return dao.listByLotteryN(lotteryid);

        else return null;
    }

    @RequestMapping(value = "/draws", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Draw> findAll(@CookieValue(value = "username") String username, @CookieValue(value = "password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.DRAW, AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;

    }

    @RequestMapping(value = "/draws", method = RequestMethod.POST)
    public String add(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Draw draw) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.DRAW, AuthProvider.INSERT)) {

            Draw drawbyno = dao.findByNoLottery(draw.getNo(),draw.getLotteryId());

            if (drawbyno != null)
                return "Error-Validation : Draw No Exists";
                try {
                    for (Drawsupervisor ds : draw.getDrawsupervisorList())
                        ds.setDrawId(draw);
                    dao.save(draw);
                    return "0";
                } catch (Exception e) {
                    return "Error-Saving : " + e.getMessage();
                }
        }
        return "Error-Inserting : You have no Permission";

    }


    @RequestMapping(value = "/draws", method = RequestMethod.PUT)
    public String update(@CookieValue(value = "username", required = false) String username, @CookieValue(value = "password", required = false) String password, @Validated @RequestBody Draw draw) {
        if (AuthProvider.isAuthorized(username, password, ModuleList.DRAW, AuthProvider.UPDATE)) {
            try {

                Draw drawfrompersistent = dao.findByNoLottery(draw.getNo(),draw.getLotteryId());
                drawfrompersistent.setPrintcount(draw.getPrintcount());
                drawfrompersistent.setSuperprice(draw.getSuperprice());
                drawfrompersistent.setLocation(draw.getLocation());
                drawfrompersistent.setDrawstatusId(draw.getDrawstatusId());
                drawfrompersistent.setDate(draw.getDate());

                drawfrompersistent.getDrawsupervisorList().clear();

                for (Drawsupervisor ds : draw.getDrawsupervisorList()) {
                    drawfrompersistent.getDrawsupervisorList().add(ds);
                    ds.setDrawId(drawfrompersistent);
                }

                dao.save(drawfrompersistent);
                return "0";
            } catch (Exception e) {
                return "Error-Saving : " + e.getMessage();
            }
        } else
            return "Error-Updating : You have no Permission";
    }


    @RequestMapping(value = "/draws", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Draw draw ) {

        if(AuthProvider.isAuthorized(username,password,ModuleList.DRAW,AuthProvider.DELETE)) {
            try {
                Draw dw = dao.getOne(draw.getId());
                dw.getDrawsupervisorList().clear();
                dao.save(dw);
                dao.delete(dw);
                return "0";

            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }

        else
            return "Error-Deleting : You have no Permission";

    }


    @RequestMapping(value = "/draws", params = {"page", "size","supervisorid", "drawstatusid", "lotteryid"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Draw> findAll(@CookieValue(value="username", required=false) String uname, @CookieValue(value="password", required=false) String pword,@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("supervisorid") Integer supervisorid, @RequestParam("drawstatusid") Integer drawstatusid, @RequestParam("lotteryid") Integer lotteryid) {

        //System.out.println(drawno+"-"+lotteryid+"-"+drawstatusid);

        if(AuthProvider.isAuthorized(uname,pword,ModuleList.DRAW,AuthProvider.SELECT)) {

            List<Draw> draws = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Draw> drawstream = draws.stream();
          //  drawstream = drawstream.filter(d -> !d.getUsername().equals("admin"));


            if (supervisorid != null) {
                drawstream = drawstream.filter(d -> {
                    for (Drawsupervisor ds : d.getDrawsupervisorList())
                        if (ds.getSupervisorId().getId().equals(supervisorid))
                            return true;
                    return false;
                });
            }

            if (drawstatusid != null)
                drawstream = drawstream.filter(d -> d.getDrawstatusId().equals(daoDrawstatus.getOne(drawstatusid)));

            if (lotteryid != null)
                drawstream = drawstream.filter(d -> d.getLotteryId().equals(daoLottery.getOne(lotteryid)));

            List<Draw> draws2 = drawstream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < draws2.size() ? start + size : draws2.size();
            Page<Draw> drawpage = new PageImpl<>(draws2.subList(start, end), PageRequest.of(page, size), draws2.size());


            return drawpage;
        }
        else
            return  null;

    }
}