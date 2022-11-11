package com.spring.springboot.springbootapplication.controller;

import com.spring.springboot.springbootapplication.dto.CarDTO;
import com.spring.springboot.springbootapplication.dto.ShopDTO;
import com.spring.springboot.springbootapplication.service.CarService;
import com.spring.springboot.springbootapplication.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarController {
    private final static String SHOW_CARS_IN_SHOP_HTML = "show-cars-in-shop";
    private final static String SHOW_ADDITIONAL_INFO_HTML = "show-additional-info";
    private final static String ADD_CAR_IN_SHOP_HTML = "add-car-in-shop";
    private final static String REDIRECT_PATTERN = "redirect:/";

    @Autowired
    private CarService carService;
    @Autowired
    private ShopService shopService;

    @RequestMapping("/{shopId}")
    public String getCarsInShop(@PathVariable final int shopId, final Model model) {
        final ShopDTO shopDTO = shopService.getShopById(shopId);
        final List<CarDTO> cars = carService.getCarsInShop(shopId);

        model.addAttribute("shop", shopDTO);
        model.addAttribute("cars", cars);

        return SHOW_CARS_IN_SHOP_HTML;
    }

    @RequestMapping("/{shopId}/{carId}")
    public String getAdditionalInfo(@PathVariable final int shopId, @PathVariable final int carId, final Model model) {
        final CarDTO carDTO = carService.getCarById(carId);

        model.addAttribute("car", carDTO);
        model.addAttribute("shopId", shopId);

        return SHOW_ADDITIONAL_INFO_HTML;
    }

    @RequestMapping("/delete/{shopId}/{carId}")
    public String deleteCarById(@PathVariable final int shopId, @PathVariable final int carId) {

        carService.deleteCarById(shopId, carId);

        return REDIRECT_PATTERN + shopId;
    }

    @RequestMapping("/{shopId}/add")
    public String addNewCar(@PathVariable final int shopId, final Model model) {
        final CarDTO carDTO = new CarDTO();

        model.addAttribute("shopId", shopId);
        model.addAttribute("car", carDTO);

        return ADD_CAR_IN_SHOP_HTML;
    }

    @RequestMapping("/{shopId}/save")
    public String saveNewCar(@PathVariable final int shopId,
                             @RequestParam("brand") final String brand,
                             @RequestParam("model") final String model,
                             @RequestParam("ageOfProduce") final int ageOfProduce,
                             @RequestParam("price") final int price) {

        carService.saveNewCar(shopId, brand, model, ageOfProduce, price);

        return REDIRECT_PATTERN + shopId;
    }
}
