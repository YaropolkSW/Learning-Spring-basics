package com.spring.springboot.springbootapplication.dao;

import com.spring.springboot.springbootapplication.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarDAO extends JpaRepository<Car, Integer> {
    @Modifying
    @Query(value = "UPDATE Client SET clientCarId = NULL WHERE clientCarId = :car")
    void deleteConnectionBetweenCarAndClient(@Param("car") final Car car);

    @Modifying
    @Query(value = "DELETE FROM shop_car WHERE car_id = :carId AND shop_id = :shopId", nativeQuery = true)
    void deleteConnectionBetweenCarAndShop(@Param("shopId") final int shopId, @Param("carId") final int carId);

    @Modifying
    @Query(value = "DELETE FROM shop_car WHERE car_id = :carId", nativeQuery = true)
    void deleteConnectionBetweenCarAndEveryShop(@Param("carId") final int carId);

}
