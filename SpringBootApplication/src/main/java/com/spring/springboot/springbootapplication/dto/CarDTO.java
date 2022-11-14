package com.spring.springboot.springbootapplication.dto;

import com.spring.springboot.springbootapplication.entity.Car;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private int carId;

    @Size(min = 1, message = "Minimum size of field is one character")
    private String brand;

    @Size(min = 1, message = "Minimum size of field is one character")
    private String model;

    @Min(value = 1886, message = "Age of produce must be only after 1886")
    private int ageOfProduce;

    @Min(value = 1, message = "Price can't be zero or less!")
    private int price;

    public static CarDTO of(final Car car) {
        final CarDTO carDTO = new CarDTO();

        carDTO.setCarId(car.getCarId());
        carDTO.setBrand(car.getBrand());
        carDTO.setModel(car.getModel());
        carDTO.setAgeOfProduce(car.getAgeOfProduce());
        carDTO.setPrice(car.getPrice());

        return carDTO;
    }
}
