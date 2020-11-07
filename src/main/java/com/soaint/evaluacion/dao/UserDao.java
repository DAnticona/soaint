package com.soaint.evaluacion.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soaint.evaluacion.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	
	public User findByUsername(String username);

}
