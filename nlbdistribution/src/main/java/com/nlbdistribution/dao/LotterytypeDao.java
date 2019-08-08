package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Lotterytype;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LotterytypeDao extends JpaRepository<Lotterytype, Integer> {

    @Query(value="SELECT new Lotterytype (lt.id,lt.name) FROM Lotterytype lt")
    List<Lotterytype> list();

    @Query("SELECT lt FROM Lotterytype lt ORDER BY lt.id DESC")
    Page<Lotterytype> findAll(Pageable of);


}
