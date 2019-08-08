package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Drawstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DrawstatusDao extends JpaRepository<Drawstatus, Integer> {


    @Query(value="SELECT new Drawstatus (ds.id,ds.name) FROM Drawstatus ds")
    List<Drawstatus> list();

}
