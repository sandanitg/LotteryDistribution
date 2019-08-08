package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Dorderstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DorderstatusDao extends JpaRepository<Dorderstatus, Integer> {

    @Query(value="SELECT new Dorderstatus (ds.id,ds.name) FROM Dorderstatus ds")
    List<Dorderstatus> list();



}
