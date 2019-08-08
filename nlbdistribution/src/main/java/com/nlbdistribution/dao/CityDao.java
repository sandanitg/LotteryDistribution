package com.nlbdistribution.dao;

import com.nlbdistribution.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityDao extends JpaRepository<City, Integer>{

    @Query(value="SELECT new City(c.id,c.name) FROM City c WHERE c.districtId.id= :districtid")
    List<City> listByDistrict(@Param("districtid") Integer districtid);

    @Query(value="SELECT new City(d.id,d.name) FROM City d")
    List<City> list();
}
