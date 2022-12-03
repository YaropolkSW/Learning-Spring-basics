package com.spring.springboot.springbootapplication.restapi;

import com.spring.springboot.springbootapplication.dto.CarDTO;
import com.spring.springboot.springbootapplication.response.DeleteCarResponse;
import com.spring.springboot.springbootapplication.response.RemoveCarFromShopResponse;
import com.spring.springboot.springbootapplication.service.CarService;
import com.spring.springboot.springbootapplication.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
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

    @GetMapping("/{id}")
    public CarDTO getAdditionalInfo(@PathVariable("id") final int id) {
        return carService.getCarById(id);
    }

    @GetMapping("/")
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping("/")
    public CarDTO saveNewCar(@RequestBody CarDTO carDTO) {
        carService.saveNewCar(carDTO);

        return carDTO;
    }

    @DeleteMapping(value = "/{carId}/shops/{shopId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RemoveCarFromShopResponse removeCarFromShopById(
        @PathVariable("shopId") final int shopId,
        @PathVariable("carId") final int carId
    ) {
        carService.removeCarFromShopById(shopId, carId);

        return new RemoveCarFromShopResponse(carId, shopId, "Car " + carService.getCarById(carId).toString()
            + " was removed from shop " + shopService.getShopById(shopId).getShopName() + ".");
    }

    @DeleteMapping(value = "/{carId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeleteCarResponse deleteCarEverywhere(@PathVariable("carId") final int carId) {
        final String carName = carService.getCarById(carId).toString();
        carService.deleteCarEverywhere(carId);

        return new DeleteCarResponse(carId, "Car " + carName + " was deleted.");
    }
}
