package com.spring.springboot.springbootapplication.controller;

import com.spring.springboot.springbootapplication.dto.CarDTO;
import com.spring.springboot.springbootapplication.dto.ShopDTO;
import com.spring.springboot.springbootapplication.service.CarService;
import com.spring.springboot.springbootapplication.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CarController {

    private CarService carService;
    private ShopService shopService;

    @Autowired
    public CarController(final CarService carService, final ShopService shopService) {
        this.carService = carService;
        this.shopService = shopService;
    }

    @GetMapping("/{shopId}")
    public String getCarsInShop(@PathVariable final int shopId, final Model model) {
        final ShopDTO shopDTO = shopService.getShopById(shopId);
        final List<CarDTO> cars = carService.getCarsInShop(shopId);

        model.addAttribute("shop", shopDTO);
        model.addAttribute("cars", cars);

        return "show-cars-in-shop";
    }

    @GetMapping("/{shopId}/{carId}")
    public String getAdditionalInfo(@PathVariable final int shopId, @PathVariable final int carId, final Model model) {
        final CarDTO carDTO = carService.getCarById(carId);

        model.addAttribute("car", carDTO);
        model.addAttribute("shopId", shopId);

        return "show-additional-info";
    }

    @GetMapping("/delete/{shopId}/{carId}")
    public String deleteCarById(@PathVariable final int shopId, @PathVariable final int carId) {

        carService.deleteCarById(shopId, carId);

        return "redirect:/" + shopId;
    }

    @GetMapping("/{shopId}/add")
    public String addNewCar(@PathVariable final int shopId, final Model model) {
        model.addAttribute("shopId", shopId);
        model.addAttribute("car", new CarDTO());

        return "add-car-in-shop";
    }

    @PostMapping("/{shopId}/save")
    public String saveNewCar(@PathVariable final int shopId, @ModelAttribute("car") @Valid final CarDTO car, final BindingResult result) {
        if (result.hasErrors()) {
            return "add-car-in-shop";
        }

        carService.saveNewCar(shopId, car.getBrand(), car.getModel(), car.getAgeOfProduce(), car.getPrice());

        return "redirect:/" + shopId;
    }
}
