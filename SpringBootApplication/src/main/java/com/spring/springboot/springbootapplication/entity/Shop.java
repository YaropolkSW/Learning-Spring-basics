package com.spring.springboot.springbootapplication.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shop")
@Getter
@Setter
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shop_id")
    private int shopId;

    @Column(name = "shop_name")
    private String shopName;

    @ManyToMany(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.MERGE})
    @JoinTable(name = "shop_car",
    joinColumns = @JoinColumn(name = "shop_id"),
    inverseJoinColumns = @JoinColumn(name = "car_id"))
    private List<Car> cars;
}
