package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Dorder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DorderDao extends JpaRepository<Dorder, Integer> {

    @Query("SELECT d FROM Dorder d ORDER BY d.id DESC")
    Page<Dorder> findAll(Pageable of);


}
