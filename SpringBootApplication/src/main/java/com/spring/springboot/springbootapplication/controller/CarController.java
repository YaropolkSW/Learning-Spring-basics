package com.spring.springboot.springbootapplication.controller;

import com.spring.springboot.springbootapplication.dao.CarDAO;
import com.spring.springboot.springbootapplication.dto.CarDTO;
import com.spring.springboot.springbootapplication.dto.ShopDTO;
import com.spring.springboot.springbootapplication.service.CarService;
import com.spring.springboot.springbootapplication.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Controller
public class CarController {

    private final CarService carService;
    private final ShopService shopService;
    private final CarDAO carDAO;

    @Autowired
    public CarController(
            final CarService carService,
            final ShopService shopService,
            final CarDAO carDAO
    ) {

        this.carService = carService;
        this.shopService = shopService;
        this.carDAO = carDAO;
    }

    @GetMapping("/shop/{shopId}/{carId}")
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

    @Transactional
    @DeleteMapping("/shop/all_cars/delete/{carId}")
    public String deleteCarEverywhere(@PathVariable final int carId) {
        carDAO.deleteConnectionBetweenCarAndClient(carId);
        carDAO.deleteConnectionBetweenCarAndEveryShop(carId);
        carDAO.deleteById(carId);

        return "redirect:/shop/all_cars";
    }
}
