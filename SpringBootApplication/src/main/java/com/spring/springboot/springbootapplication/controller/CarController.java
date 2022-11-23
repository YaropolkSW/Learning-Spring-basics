package com.spring.springboot.springbootapplication.controller;

import com.spring.springboot.springbootapplication.dto.CarDTO;
import com.spring.springboot.springbootapplication.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(
            final CarService carService
    ) {

        this.carService = carService;
    }

    @GetMapping("/shop/{shopId}/car/{carId}")
    public String getAdditionalInfo(@PathVariable final int shopId, @PathVariable final int carId, final Model model) {
        final CarDTO carDTO = carService.getCarById(carId);

        model.addAttribute("car", carDTO);
        model.addAttribute("shopId", shopId);

        return "show-additional-info";
    }

    @DeleteMapping("/shop/remove/{shopId}/{carId}")
    public String removeCarFromShopById(@PathVariable final int shopId, @PathVariable final int carId) {

        carService.removeCarFromShopById(shopId, carId);

        return "redirect:/shop/" + shopId;
    }

    @DeleteMapping("/shop/all_cars/delete/{carId}")
    public String deleteCarEverywhere(@PathVariable final int carId) {
        carService.deleteCarEverywhere(carId);

        return "redirect:/shop/all_cars";
    }
}
