package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Prize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrizeDao extends JpaRepository<Prize, Integer> {

    @Query(value="SELECT new Prize(p.id,p.name) FROM Prize p")
    List<Prize> list();
}
