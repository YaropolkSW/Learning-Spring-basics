package com.spring.springboot.springbootapplication.service;

import com.spring.springboot.springbootapplication.dao.CarDAO;
import com.spring.springboot.springbootapplication.dao.ShopDAO;
import com.spring.springboot.springbootapplication.dto.CarDTO;
import com.spring.springboot.springbootapplication.entity.Car;
import com.spring.springboot.springbootapplication.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private static final String NO_SUCH_CAR_EXC_MESSAGE = "There is no car with Id = %s";
    private static final String NO_SUCH_SHOP_EXC_MESSAGE = "There is no shop with Id = %s";

    private CarDAO carDAO;
    private ShopDAO shopDAO;

    @Autowired
    public CarService(final CarDAO carDAO, final ShopDAO shopDAO) {
        this.carDAO = carDAO;
        this.shopDAO = shopDAO;
    }

    public List<CarDTO> getCarsInShop(final int shopId) {
        final List<CarDTO> carsDTO = carDAO.getCarsInShopByShopId(shopId).
                stream().map(CarDTO::of).collect(Collectors.toList());

        return carsDTO;
    }

    public CarDTO getCarById(final int carId) {
        final CarDTO carDTO = CarDTO.of(carDAO.findById(carId).orElseThrow(() ->
                new EntityNotFoundException(String.format(NO_SUCH_CAR_EXC_MESSAGE, carId))));

        return carDTO;
    }

    public void deleteCarById(final int shopId, final int carId) {
        carDAO.deleteConnectionBetweenCarAndClient(carId);
        carDAO.deleteConnectionBetweenCarAndShop(shopId, carId);
    }

    public void saveNewCar(final int shopId,
                           final String brand,
                           final String model,
                           final int ageOfProduce,
                           final int price) {

        final Car car = new Car();

        car.setBrand(brand);
        car.setModel(model);
        car.setAgeOfProduce(ageOfProduce);
        car.setPrice(price);
        carDAO.save(car);

        final Shop shop = shopDAO.findById(shopId).orElseThrow(() ->
                new EntityNotFoundException(String.format(NO_SUCH_SHOP_EXC_MESSAGE, shopId)));

        final List<Car> cars = shop.getCars();

        cars.add(car);
        shop.setCars(cars);
        shopDAO.save(shop);
    }
}
