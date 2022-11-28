package com.spring.springboot.springbootapplication.dao;

import com.spring.springboot.springbootapplication.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShopDAO extends JpaRepository<Shop, Integer> {

    @Modifying
    @Query(value = "INSERT INTO shop_car(shop_id, car_id) VALUES(:shopId, :carId)",
        nativeQuery = true)
    void saveCarInShop(@Param("shopId") final int shopId, @Param("carId") final int carId);
}
