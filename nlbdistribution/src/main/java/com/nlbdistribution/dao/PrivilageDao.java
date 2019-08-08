package com.nlbdistribution.dao;

import com.nlbdistribution.entity.Module;
import com.nlbdistribution.entity.Privilage;
import com.nlbdistribution.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PrivilageDao extends JpaRepository<Privilage, Integer> {

    @Query("SELECT p FROM Privilage p WHERE p.roleId= :role AND p.moduleId= :module")
    Privilage findByRoleModule(@Param("role")Role role, @Param("module")Module module );


}
