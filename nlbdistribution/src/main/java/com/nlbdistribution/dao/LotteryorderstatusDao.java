package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Lotteryorderstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LotteryorderstatusDao extends JpaRepository<Lotteryorderstatus, Integer> {

    @Query(value="SELECT new Lotteryorderstatus (l.id,l.name) FROM Lotteryorderstatus l")
    List<Lotteryorderstatus> list();

}
