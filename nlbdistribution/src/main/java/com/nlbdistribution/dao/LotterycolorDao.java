package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Lotterycolor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LotterycolorDao extends JpaRepository<Lotterycolor, Integer> {

    @Query(value="SELECT new Lotterycolor (lc.id,lc.name) FROM Lotterycolor lc")
    List<Lotterycolor> list();


}
