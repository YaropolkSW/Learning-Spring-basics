package com.spring.springboot.springbootapplication.controller;

import com.spring.springboot.springbootapplication.dao.ShopDAO;
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
public class MainController {
    private final CarService carService;
    private final ShopService shopService;

    @Autowired
    public MainController(
            final CarService carService,
            final ShopService shopService
    ) {

        this.carService = carService;
        this.shopService = shopService;
    }

    @GetMapping("/shop")
    public String getAllShops(final Model model) {
        final List<ShopDTO> shops = shopService.getAllShops();

        model.addAttribute("shops", shops);

        return "show-all-shops";
    }

    @GetMapping("/shop/all_cars")
    public String getAllCars(final Model model) {
        final List<CarDTO> cars = carService.getAllCars();

        model.addAttribute("cars", cars);

        return "show-all-cars";
    }

    @GetMapping("/shop/all_cars/add_new_car")
    public String addNewCar(final Model model) {
        model.addAttribute("car", new CarDTO());

        return "add-new-car";
    }

    @PostMapping("/shop/all_cars/save_new_car")
    public String saveNewCar(
            @ModelAttribute("car") @Valid final CarDTO car,
            final BindingResult result
    ) {

        if (result.hasErrors()) {
            return "add-new-car";
        }

        carService.saveNewCar(car);

        return "redirect:/shop/all_cars";
    }
}
