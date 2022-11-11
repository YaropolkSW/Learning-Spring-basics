package com.spring.springboot.springbootapplication.dao;

import com.spring.springboot.springbootapplication.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDAO extends JpaRepository<Car, Integer> {
    Car findCarByCarId(final int carId);
}
