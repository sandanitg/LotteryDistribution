package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Digitoption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DigitoptionDao extends JpaRepository<Digitoption, Integer> {

    @Query(value="SELECT new Digitoption(d.id,d.name) FROM Digitoption d")
    List<Digitoption> list();

    @Query(value="SELECT new Digitoption (d.id, d.name) FROM Digitoption d WHERE d.id IN (SELECT lg.id FROM Lotterytypedigitoption lg WHERE lg.lotterytypeId.id= :lotterytypeid)")
    List<Digitoption> listByLottery(@Param("lotterytypeid")Integer lotterytypeid);



}
