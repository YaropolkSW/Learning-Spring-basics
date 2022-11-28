package com.spring.springboot.springbootapplication.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "age_of_produce")
    private int ageOfProduce;

    @Column(name = "price")
    private int price;

    @ManyToMany(cascade = {CascadeType.PERSIST,
        CascadeType.DETACH,
        CascadeType.REFRESH,
        CascadeType.MERGE})
    @JoinTable(name = "shop_car",
        joinColumns = @JoinColumn(name = "car_id"),
        inverseJoinColumns = @JoinColumn(name = "shop_id"))
    private List<Shop> shops;
}
