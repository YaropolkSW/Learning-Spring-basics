package com.spring.springboot.springbootapplication.controller;

import com.spring.springboot.springbootapplication.dto.CarDTO;
import com.spring.springboot.springbootapplication.dto.ShopDTO;
import com.spring.springboot.springbootapplication.service.CarService;
import com.spring.springboot.springbootapplication.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainRestController {
    private final CarService carService;
    private final ShopService shopService;

    @Autowired
    public MainRestController(
        final CarService carService,
        final ShopService shopService
    ) {
        this.carService = carService;
        this.shopService = shopService;
    }

    @GetMapping("/shops")
    public List<ShopDTO> getAllShops() {
        return shopService.getAllShops();
    }

    @GetMapping("/cars")
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping("/cars")
    public CarDTO saveNewCar(@RequestBody CarDTO carDTO) {
        carService.saveNewCar(carDTO);

        return carDTO;
    }
}
