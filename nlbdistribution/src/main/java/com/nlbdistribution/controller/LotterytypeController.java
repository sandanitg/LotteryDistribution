package com.nlbdistribution.controller;

import com.nlbdistribution.dao.LotterytypeDao;
import com.nlbdistribution.dao.PrizeDao;
import com.nlbdistribution.entity.Lotterytype;
import com.nlbdistribution.entity.Lotterytypedigitoption;
import com.nlbdistribution.entity.Prize;
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
public class LotterytypeController {

    @Autowired
    private LotterytypeDao dao;

    @Autowired
    private PrizeDao daoPrize;

    @RequestMapping(value = "/lotterytypes/list", method = RequestMethod.GET, produces = "application/json")
    public List<Lotterytype> lotterytypes() {
        return dao.list();
    }

    @RequestMapping(value = "/lotterytypes", params = {"page", "size"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Lotterytype> findAll(@CookieValue(value="username") String username, @CookieValue(value="password") String password, @RequestParam("page") int page, @RequestParam("size") int size) {
        if(AuthProvider.isAuthorized(username,password, ModuleList.LOTTERYTYPE,AuthProvider.SELECT)) {
            return dao.findAll(PageRequest.of(page, size));
        }
        return null;
    }


    @RequestMapping(value = "/lotterytypes", method = RequestMethod.POST)
    public String add(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Lotterytype lotterytype) {


        for (Lotterytypedigitoption l: lotterytype.getLotterytypedigitoptionList())
            l.setLotterytypeId(lotterytype);

        for (Prize l: lotterytype.getPrizeList())
            l.setLotterytypeId(lotterytype);

        dao.save(lotterytype);
        return "0";

    }

    @RequestMapping(value = "/lotterytypes", method = RequestMethod.PUT)
    public String update(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@Validated @RequestBody Lotterytype lotterytype) {
        // if(AuthProvider.isAuthorized(username,password,"Invoice",AuthProvider.UPDATE)) {
        try {

            Lotterytype lotterytypefrompersistent = dao.getOne(lotterytype.getId());
            lotterytypefrompersistent.setDigitformat(lotterytype.getDigitformat());
            lotterytypefrompersistent.setDigitsizeId(lotterytype.getDigitsizeId());
            lotterytypefrompersistent.setLotterytypestatusId(lotterytype.getLotterytypestatusId());
            lotterytypefrompersistent.getPrizeList().clear();
            lotterytypefrompersistent.getLotterytypedigitoptionList().clear();

            for (Lotterytypedigitoption lo : lotterytype.getLotterytypedigitoptionList()) {
                lotterytypefrompersistent.getLotterytypedigitoptionList().add(lo);
                lo.setLotterytypeId(lotterytypefrompersistent);
            }

            for(Prize p : lotterytype.getPrizeList()) {
                lotterytypefrompersistent.getPrizeList().add(p);
                p.setLotterytypeId(lotterytypefrompersistent);
            }

            dao.save(lotterytypefrompersistent);
            return "0";
        }
        catch(Exception e) {
            return "Error-Saving : " + e.getMessage();
        }
    }

    @RequestMapping(value = "/lotterytypes", method = RequestMethod.DELETE)
    public String delete(@CookieValue(value="username", required=false) String username, @CookieValue(value="password", required=false) String password,@RequestBody Lotterytype lotterytype ) {

        //System.out.println("delete");

        if (AuthProvider.isAuthorized(username, password, ModuleList.LOTTERYTYPE, AuthProvider.DELETE)) {
            try {
                Lotterytype lt = dao.getOne(lotterytype.getId());
                lt.getPrizeList().remove(lt);
                dao.save(lt);
                dao.delete(lt);
                return "0";

            } catch (Exception e) {
                return "Error-Deleting : " + e.getMessage();
            }
        }

        else
            return "Error-Deleting : You have no Permission";


    }

    @RequestMapping(value = "/lotterytypes", params = {"page","size","digitformat"}, method = RequestMethod.GET, produces = "application/json")
    public Page<Lotterytype> findAll(@CookieValue(value ="username", required = false) String uname, @CookieValue(value = "password", required = false) String pword, @RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("digitformat") String digitformat) {
        //System.out.println(ps);
        if (AuthProvider.isAuthorized(uname, pword, ModuleList.LOTTERYTYPE, AuthProvider.SELECT)) {

            List<Lotterytype>lotterytypes = dao.findAll(Sort.by(Sort.Direction.DESC, "id"));
            Stream<Lotterytype> lotterytypeStream = lotterytypes.stream();

            /*if (digitformat != null)
                purchaseorderStream = purchaseorderStream.filter(p -> p.getPostatusId().equals(daoPostatus.getOne(ps)));*/

            lotterytypeStream = lotterytypeStream.filter(e -> e.getDigitformat().startsWith(digitformat));

            List<Lotterytype> lotterytype2 =lotterytypeStream.collect(Collectors.toList());

            int start = page * size;
            int end = start + size < lotterytype2.size() ? start + size : lotterytype2.size();
            Page<Lotterytype> dfpage = new PageImpl<>(lotterytype2.subList(start, end), PageRequest.of(page, size), lotterytype2.size());


            return dfpage;
        }
        else
            return null;

    }
}
