package com.nlbdistribution.dao;


import com.nlbdistribution.entity.Sessionlog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionDao extends JpaRepository<Sessionlog, Integer>
{

}