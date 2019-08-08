package com.nlbdistribution.controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class ReportProvider {

    public static List<ReportEntitySystemAccessAnalysis> getSystemAccessAnalysisReport(LocalDateTime startdate, LocalDateTime enddate) {


        try {
            AuthProvider.setConnection();
            Statement st = AuthProvider.connection.createStatement();
            String query = "SELECT d.name as name, count(*) as attempt FROM nlb.sessionlog as s, nlb.user as u, nlb.employee as e, nlb.designation as d where s.user_id=u.id and u.employee_id=e.id and e.designation_id=d.id and s.logintime between '"+startdate+"' and '"+enddate+"' group by d.id ;";
            ResultSet rs = st.executeQuery(query);

            List<ReportEntitySystemAccessAnalysis> list = new ArrayList<>();

            while (rs.next()) {
                ReportEntitySystemAccessAnalysis report = new ReportEntitySystemAccessAnalysis(rs.getString("name"), rs.getInt("attempt"));
                list.add(report);
            }
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }



    }


    public static List<ReportEntityDrawAnalysis> getDrawAnalysisReport(LocalDate startdate, LocalDate enddate) {


        try {
            AuthProvider.setConnection();
            Statement st = AuthProvider.connection.createStatement();
            String query = "SELECT d.fullname as dealer, sum(l.quantity) as Quantity FROM nlb.dealer as d, nlb.lotteryorder as l where d.id=l.dealer_id and l.date between '"+startdate+"' and '"+enddate+"' group by d.id ;"
                    ;
            ResultSet rs = st.executeQuery(query);

            List<ReportEntityDrawAnalysis> list = new ArrayList<>();

            while (rs.next()) {
                ReportEntityDrawAnalysis report = new ReportEntityDrawAnalysis(rs.getString("dealer"), rs.getInt("quantity"));
                list.add(report);
            }
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }



    }


    public static List<ReportEntityDealeranalysis> getDealerAnalysisReport(LocalDate startdate, LocalDate enddate) {


        try {
            AuthProvider.setConnection();
            Statement st = AuthProvider.connection.createStatement();
            String query = "SELECT l.name as lotteryn, sum(lo.quantity) as Quantity FROM nlb.lotteryorder as lo, nlb.lottery as l, nlb.draw as d, nlb.dealer as de where lo.dealer_id=de.id and lo.draw_id=d.id and d.lottery_id=l.id and lo.date between '2018-10-01' and '2018-11-30' and de.id=2 group by l.name";


            ResultSet rs = st.executeQuery(query);

            List<ReportEntityDealeranalysis> list = new ArrayList<>();

            while (rs.next()) {
                ReportEntityDealeranalysis report = new ReportEntityDealeranalysis(rs.getString("lotteryn"), rs.getInt("quantity"));
                list.add(report);
            }
            return list;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }



    }
}



