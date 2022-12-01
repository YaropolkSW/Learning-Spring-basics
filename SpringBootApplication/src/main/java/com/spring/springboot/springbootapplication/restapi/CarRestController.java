package com.spring.springboot.springbootapplication.restapi;

import com.spring.springboot.springbootapplication.dto.CarDTO;
import com.spring.springboot.springbootapplication.service.CarService;
import com.spring.springboot.springbootapplication.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CarRestController {
    private final CarService carService;
    private final ShopService shopService;

    @Autowired
    public CarRestController(
        final CarService carService,
        final ShopService shopService
    ) {
        this.carService = carService;
        this.shopService = shopService;
    }

    @GetMapping("/cars/{id}")
    public CarDTO getAdditionalInfo(@PathVariable("id") final int id) {
        return carService.getCarById(id);
    }

    @DeleteMapping("/shops/{shopId}/cars/{carId}")
    public String removeCarFromShopById(
        @PathVariable("shopId") final int shopId,
        @PathVariable("carId") final int carId
    ) {
        carService.removeCarFromShopById(shopId, carId);

        return "Car with id = " + carId + " was removed from shop "
            + shopService.getShopById(shopId).getShopName() + ".";
    }

    @DeleteMapping("/cars/{carId}")
    public String deleteCarEverywhere(@PathVariable("carId") final int carId) {
        carService.deleteCarEverywhere(carId);

        return "Car with id = " + carId + " was deleted.";
    }
}
