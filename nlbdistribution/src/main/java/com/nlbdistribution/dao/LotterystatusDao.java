package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Lotterystatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LotterystatusDao extends JpaRepository<Lotterystatus, Integer> {

    @Query(value="SELECT new Lotterystatus (ls.id,ls.name) FROM Lotterystatus ls")
    List<Lotterystatus> list();

}
