package com.spring.springboot.springbootapplication.dto;

import com.spring.springboot.springbootapplication.entity.Car;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CarDTO {
    private int id;

    @Size(min = 1, message = "Minimum size of field is one character")
    private String brand;

    @Size(min = 1, message = "Minimum size of field is one character")
    private String model;

    @Min(value = 1886, message = "Age of produce must be only after 1886")
    private int ageOfProduce;

    @Min(value = 1, message = "Price can't be zero or less!")
    private int price;

    public static CarDTO of(final Car car) {
        return CarDTO.builder()
            .id(car.getId())
            .brand(car.getBrand())
            .model(car.getModel())
            .ageOfProduce(car.getAgeOfProduce())
            .price(car.getPrice())
            .build();
    }

    @Override
    public String toString() {
        return brand + " " + model;
    }
}
