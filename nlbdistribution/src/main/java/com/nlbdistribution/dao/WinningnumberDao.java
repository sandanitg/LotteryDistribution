package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Winningnumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WinningnumberDao extends JpaRepository<Winningnumber, Integer> {


    @Query(value="SELECT new Winningnumber (wn.id,wn.val) FROM Winningnumber wn")
    List<Winningnumber> list();

}
