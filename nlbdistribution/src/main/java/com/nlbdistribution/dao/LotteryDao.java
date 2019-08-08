package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Lottery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LotteryDao extends JpaRepository<Lottery,Integer> {

    @Query("SELECT l FROM Lottery l ORDER BY l.id DESC")
    Page<Lottery> findAll(Pageable of);

    @Query("SELECT l FROM Lottery l WHERE l.name= :lotteryname")
    Lottery findByName(@Param("lotteryname")String lotteryname);

    @Query(value="SELECT new Lottery(l.id,l.name) FROM Lottery l")
    List<Lottery> list();


    @Query("SELECT new Lottery(l.id, l.name) FROM Lottery l WHERE  l.lotterytypeId.id= :lotterytypeid")
    List<Lottery> listByLotterytype(@Param("lotterytypeid")Integer lotterytypeid);




}

