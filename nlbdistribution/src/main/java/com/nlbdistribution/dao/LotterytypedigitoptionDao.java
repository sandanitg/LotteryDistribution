package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Lotterytypedigitoption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LotterytypedigitoptionDao extends JpaRepository<Lotterytypedigitoption, Integer> {

    @Query(value="SELECT new Lotterytypedigitoption(l.id,l.digitoptionId) FROM Lotterytypedigitoption l WHERE l.lotterytypeId.id= :lotterytypeid")
    List<Lotterytypedigitoption> listByLotterytype(@Param("lotterytypeid")Integer lotterytypeid);

}
