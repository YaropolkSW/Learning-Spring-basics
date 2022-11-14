package com.spring.springboot.springbootapplication.dao;

import com.spring.springboot.springbootapplication.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopDAO extends JpaRepository<Shop, Integer> {
}
