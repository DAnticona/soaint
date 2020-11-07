package com.soaint.evaluacion.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soaint.evaluacion.entity.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

}
