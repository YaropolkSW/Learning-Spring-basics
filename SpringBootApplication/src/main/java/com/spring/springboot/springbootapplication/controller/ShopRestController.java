package com.spring.springboot.springbootapplication.controller;

import com.spring.springboot.springbootapplication.dto.CarDTO;
import com.spring.springboot.springbootapplication.service.CarService;
import com.spring.springboot.springbootapplication.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ShopRestController {
    private final CarService carService;
    private final ShopService shopService;

    @Autowired
    public ShopRestController(
        final CarService carService,
        final ShopService shopService
    ) {
        this.carService = carService;
        this.shopService = shopService;
    }

    @GetMapping("/shops/{shopId}")
    public List<CarDTO> getCarsInShop(@PathVariable("shopId") final int shopId) {
        return carService.getCarsInShop(shopId);
    }

    @PostMapping("/shop/{shopId}")
    public CarDTO saveCarToShop(
        @PathVariable("shopId") final int shopId,
        @RequestBody final CarDTO carDTO
    ) {
        final String carBrand = carDTO.getBrand();
        final String carModel = carDTO.getModel();

        carService.getAllCars().stream()
            .filter(car -> (car.getBrand().equals(carBrand)) && (car.getModel().equals(carModel)))
            .forEach(car -> shopService.saveCarInShop(shopId, car.getId()));

        return carDTO;
    }
}
