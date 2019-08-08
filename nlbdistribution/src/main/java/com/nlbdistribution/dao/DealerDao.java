package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Dealer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DealerDao extends JpaRepository<Dealer, Integer> {

    @Query(value="SELECT new Dealer (d.id,d.fullname) FROM Dealer d")
    List<Dealer> list();

    @Query("SELECT d FROM Dealer d ORDER BY d.id DESC")
    Page<Dealer> findAll(Pageable of);

    @Query("SELECT d FROM Dealer d WHERE d.fullname= :fullname")
    Dealer findByFullname(@Param("fullname")String fullname);

    @Query("SELECT d FROM Dealer d WHERE d.nic= :nic")
    Dealer findByNic(@Param("nic")String nic);
}
