package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Draw;
import com.nlbdistribution.entity.Lottery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


public interface DrawDao extends JpaRepository<Draw, Integer> {


    @Query("SELECT e FROM Draw e ORDER BY e.id DESC")
    Page<Draw> findAll(Pageable of);


    @Query("SELECT d FROM Draw d WHERE d.no= :no AND d.lotteryId= :lotteryid")
    Draw findByNoLottery(@Param("no")Integer no,@Param("lotteryid")Lottery lotteryid);


    @Query(value="SELECT new Draw(d.id,d.no,d.lotteryId) FROM Draw d")
    List<Draw> list();

    @Query(value="SELECT new Draw(d.id, d.no) FROM Draw d WHERE d.lotteryId.id=(SELECT l.id FROM Lottery l WHERE l.lotterytypeId.id= :lotterytypeid AND l.dayId.id=:dayid) and d.date >= current_date ")
    List<Draw> listByLottery(@Param("lotterytypeid")Integer lotterytypeid,@Param("dayid") Integer dayid);

    @Query(value="SELECT new Draw(d.id, d.no) FROM Draw d WHERE d.lotteryId.id=:lotteryid")
    List<Draw> listByLotteryN(@Param("lotteryid")Integer lotteryid);

  /*  @Query(value="SELECT new Draw(d.id, d.no) FROM Draw d WHERE d.lotteryorderId.id=(SELECT l.id FROM Lotteryorder l WHERE l.dealerId.id= :dealerid AND l.drawId.id=:drawid)")
    List<Draw> listByDealer(@Param("dealerid")Integer dealerid,@Param("drawid") Integer drawid);*/


    /*@Modifying
    @Transactional
    @Query("delete from Draw d where id = ?1")
    void deleteByQuery(Integer id);
*/

    // and d.date >= current_date
}