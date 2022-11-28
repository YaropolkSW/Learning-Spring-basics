package com.spring.springboot.springbootapplication.controller;

import com.spring.springboot.springbootapplication.dto.CarDTO;
import com.spring.springboot.springbootapplication.dto.ShopDTO;
import com.spring.springboot.springbootapplication.service.CarService;
import com.spring.springboot.springbootapplication.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ShopController {
    private static final String SPACE_PATTERN = " ";

    private final ShopService shopService;
    private final CarService carService;

    @Autowired
    public ShopController(
        final ShopService shopService,
        final CarService carService
    ) {

        this.shopService = shopService;
        this.carService = carService;
    }

    @GetMapping("/shop/{shopId}")
    public String getCarsInShop(@PathVariable final int shopId, final Model model) {
        final ShopDTO shopDTO = shopService.getShopById(shopId);
        final List<CarDTO> cars = carService.getCarsInShop(shopId);

        model.addAttribute("shop", shopDTO);
        model.addAttribute("cars", cars);

        return "show-cars-in-shop";
    }

    @GetMapping("/shop/{shopId}/add_car_to_shop")
    public String addCarToShop(@PathVariable final int shopId, final Model model) {
        final List<CarDTO> carsInShop = carService.getCarsInShop(shopId);
        final List<CarDTO> carsNotInShop = carService
            .getAllCars()
            .stream()
            .filter(x -> !carsInShop.contains(x))
            .collect(Collectors.toList());

        final ShopDTO shopDTO = shopService.getShopById(shopId);

        model.addAttribute("cars", carsNotInShop);
        model.addAttribute("shop", shopDTO);

        return "add-new-car-in-shop";
    }

    @PostMapping("/shop/{shopId}/save_car_to_shop")
    public String saveCarToShop(
        @PathVariable final int shopId,
        @RequestParam("car") final String carName
    ) {
        if (carName.split(SPACE_PATTERN).length == 1) {
            return "redirect:/shop/" + shopId;
        }

        final String brand = carName.split(SPACE_PATTERN)[0];
        final String model = carName.split(SPACE_PATTERN)[1];

        carService.getAllCars().stream()
            .filter(car -> (car.getBrand().equals(brand) && car.getModel().equals(model)))
            .forEach(car -> shopService.saveCarInShop(shopId, car.getId()));

        return "redirect:/shop/" + shopId;
    }
}
