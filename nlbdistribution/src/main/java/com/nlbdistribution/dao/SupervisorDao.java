package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupervisorDao extends JpaRepository<Supervisor, Integer>{


    @Query(value="SELECT new Supervisor (s.id,s.name) FROM Supervisor s")
    List<Supervisor> list();


}
