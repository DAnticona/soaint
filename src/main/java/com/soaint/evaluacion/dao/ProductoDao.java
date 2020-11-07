package com.soaint.evaluacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soaint.evaluacion.entity.Producto;

@Repository
public interface ProductoDao extends JpaRepository<Producto, Integer>{

}
