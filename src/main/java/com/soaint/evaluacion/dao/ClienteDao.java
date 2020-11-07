package com.soaint.evaluacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soaint.evaluacion.entity.Cliente;

@Repository
public interface ClienteDao extends JpaRepository<Cliente, Integer> {

}
