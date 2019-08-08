package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Draw;
import com.nlbdistribution.entity.Lotterytype;
import com.nlbdistribution.entity.Winning;
import com.nlbdistribution.entity.Winningnumber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WinningDao extends JpaRepository<Winning, Integer> {

    @Query("SELECT w FROM Winning w ORDER BY w.id DESC")
    Page<Winning> findAll(Pageable of);

   /* @Query("SELECT w FROM Winning w WHERE w.drawId= :drawid AND w.lotterytypeid= :lotterytypeid")
    Winning findByDrawLotterytype(@Param("drawid")Draw drawid, @Param("lotterytypeid")Lotterytype lotterytypeid);
*/

   @Query("SELECT w FROM Winning w WHERE w.id= :id")
    Winning findByWinningId(@Param("id")Integer id);


}
