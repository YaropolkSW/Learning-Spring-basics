package com.spring.springboot.springbootapplication.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private int carId;

    private String brand;

    private String model;

    private int ageOfProduce;

    private int price;
}
