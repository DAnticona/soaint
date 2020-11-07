package com.soaint.evaluacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soaint.evaluacion.entity.DetalleVenta;

@Repository
public interface DetalleVentaDao extends JpaRepository<DetalleVenta, Integer>{

}
