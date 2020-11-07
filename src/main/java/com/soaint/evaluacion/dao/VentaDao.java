package com.soaint.evaluacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soaint.evaluacion.entity.Venta;

@Repository
public interface VentaDao extends JpaRepository<Venta, Integer> {

}
