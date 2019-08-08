package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Digitsize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DigitsizeDao extends JpaRepository<Digitsize, Integer> {

    @Query(value="SELECT new Digitsize(d.id,d.name) FROM Digitsize d")
    List<Digitsize> list();
}
