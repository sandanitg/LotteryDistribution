package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Lotterytypestatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LotterytypestatusDao extends JpaRepository<Lotterytypestatus, Integer> {

    @Query(value="SELECT new Lotterytypestatus(l.id,l.name) FROM Lotterytypestatus l")
    List<Lotterytypestatus> list();
}
