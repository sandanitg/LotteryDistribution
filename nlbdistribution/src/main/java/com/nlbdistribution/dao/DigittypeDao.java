package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Digittype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DigittypeDao extends JpaRepository<Digittype, Integer> {

    @Query(value="SELECT new Digittype(dg.id,dg.name) FROM Digittype dg")
    List<Digittype> list();
}
