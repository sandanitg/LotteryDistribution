package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Dealerstatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DealerstatusDao extends JpaRepository<Dealerstatus, Integer>{

    @Query(value="SELECT new Dealerstatus(ds.id,ds.name) FROM Dealerstatus ds")
    List<Dealerstatus> list();

}
