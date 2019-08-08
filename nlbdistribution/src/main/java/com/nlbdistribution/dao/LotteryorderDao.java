package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Dealer;
import com.nlbdistribution.entity.Draw;
import com.nlbdistribution.entity.Lotteryorder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LotteryorderDao extends JpaRepository<Lotteryorder, Integer> {


    @Query("SELECT l FROM Lotteryorder l ORDER BY l.id DESC")
    Page<Lotteryorder> findAll(Pageable of);

    @Query("SELECT l FROM Lotteryorder l WHERE l.drawId= :drawid AND l.dealerId= :dealerid")
    Lotteryorder findByDrawDealer(@Param("drawid")Draw drawid, @Param("dealerid")Dealer dealerid);

    @Query(value="SELECT new Lotteryorder(l.drawId, l.dealerId) FROM Lotteryorder l")
    List<Lotteryorder> list();

}
