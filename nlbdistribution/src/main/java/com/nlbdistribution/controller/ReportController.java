package com.nlbdistribution.controller;


import com.nlbdistribution.util.ModuleList;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class ReportController {



    @RequestMapping(value = "/reports/systemaccessanalysis", method = RequestMethod.GET, produces = "application/json")
    public List<ReportEntitySystemAccessAnalysis> systemaccessanalysis(@CookieValue(value="username") String username, @CookieValue(value="password") String password) {
if (AuthProvider.isAuthorized(username,password, ModuleList.EMPLOYEE,AuthProvider.SELECT)) {
    LocalDateTime startdate = LocalDateTime.now().minusDays(35);
    LocalDateTime enddate = LocalDateTime.now().plusDays(1);
    return ReportProvider.getSystemAccessAnalysisReport(startdate, enddate);
}
else return  null;


    }

    @RequestMapping(value = "/reports/drawanalysis", params = {"startdate", "enddate"}, method = RequestMethod.GET, produces = "application/json")
    public List<ReportEntityDrawAnalysis> drawanalysis2(@CookieValue(value="username") String username, @CookieValue(value="password") String password,@RequestParam("startdate") String startdate,@RequestParam("enddate") String enddate) {
        if (AuthProvider.isAuthorized(username,password, ModuleList.DEALER,AuthProvider.SELECT)) {
            LocalDate stdate=LocalDate.parse(startdate);
            LocalDate endate=LocalDate.parse(enddate);
            return ReportProvider.getDrawAnalysisReport(stdate,endate);
        }
        else return  null;
    }


    @RequestMapping(value = "/reports/dealeranalysis", params = {"startdate", "enddate"}, method = RequestMethod.GET, produces = "application/json")
    public List<ReportEntityDealeranalysis> dealeranalysis2(@CookieValue(value="username") String username, @CookieValue(value="password") String password,@RequestParam("startdate") String startdate,@RequestParam("enddate") String enddate) {
        if (AuthProvider.isAuthorized(username,password, ModuleList.LOTTERYORDER,AuthProvider.SELECT)) {
            LocalDate stdate=LocalDate.parse(startdate);
            LocalDate endate=LocalDate.parse(enddate);
            return ReportProvider.getDealerAnalysisReport(stdate,endate);
        }
        else return  null;
    }



}
