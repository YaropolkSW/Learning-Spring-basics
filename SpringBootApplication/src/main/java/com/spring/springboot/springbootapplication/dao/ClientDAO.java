package com.spring.springboot.springbootapplication.dao;

import com.spring.springboot.springbootapplication.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDAO extends JpaRepository<Client, Integer> {
}
