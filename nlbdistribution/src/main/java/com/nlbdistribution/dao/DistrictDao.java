package com.nlbdistribution.dao;

import com.nlbdistribution.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DistrictDao extends JpaRepository<District, Integer> {

    @Query(value="SELECT new District(d.id,d.name) FROM District d")
    List<District> list();
}
