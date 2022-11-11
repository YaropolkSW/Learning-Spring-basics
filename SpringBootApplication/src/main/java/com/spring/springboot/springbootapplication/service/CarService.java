package com.spring.springboot.springbootapplication.service;

import com.spring.springboot.springbootapplication.dao.CarDAO;
import com.spring.springboot.springbootapplication.dao.ClientDAO;
import com.spring.springboot.springbootapplication.dao.ShopDAO;
import com.spring.springboot.springbootapplication.dto.CarDTO;
import com.spring.springboot.springbootapplication.entity.Car;
import com.spring.springboot.springbootapplication.entity.Client;
import com.spring.springboot.springbootapplication.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarDAO carDAO;
    @Autowired
    private ShopDAO shopDAO;
    @Autowired
    private ClientDAO clientDAO;
    @Autowired
    private EntityManager entityManager;

    public List<CarDTO> getCarsInShop(final int id) {
        final List<CarDTO> carsDTO = new ArrayList<>();
        final Query carQuery = entityManager.createQuery("SELECT c.carId, c.brand, c.model, c.ageOfProduce, c.price" +
                " FROM Car c LEFT JOIN c.shops s WHERE s.shopId = :id");

        carQuery.setParameter("id", id);

        final List<Object[]> cars = carQuery.getResultList();

        for (final Object[] car : cars) {
            final CarDTO carDTO = new CarDTO();

            carDTO.setCarId((int) car[0]);
            carDTO.setBrand((String) car[1]);
            carDTO.setModel((String) car[2]);
            carDTO.setAgeOfProduce((int) car[3]);
            carDTO.setPrice((int) car[4]);

            carsDTO.add(carDTO);
        }

        return carsDTO;
    }

    public CarDTO getCarById(final int id) {
        final CarDTO carDTO = new CarDTO();
        final Car car = carDAO.findCarByCarId(id);

        carDTO.setCarId(id);
        carDTO.setBrand(car.getBrand());
        carDTO.setModel(car.getModel());
        carDTO.setAgeOfProduce(car.getAgeOfProduce());
        carDTO.setPrice(car.getPrice());

        return carDTO;
    }

    public void deleteCarById(final int shopId, final int carId) {
        final Car car = carDAO.findCarByCarId(carId);
        final Shop shop = shopDAO.getShopByShopId(shopId);
        final List<Car> cars = shop.getCars();

        cars.remove(car);
        shop.setCars(cars);
        shopDAO.save(shop);

        final Query clientQuery = entityManager.createQuery("SELECT c.clientId, c.clientName, c.clientCity, c.clientCarId" +
                " FROM Client c WHERE c.clientCarId = :id"); //There should be Entity, but why not car id? With Entity this works just fine

        clientQuery.setParameter("id", car);

        final List<Object[]> objects = clientQuery.getResultList();

        for (final Object[] obj : objects) {
            if (((int) obj[0]) != 0) {
                final Client client = new Client();

                client.setClientId((int) obj[0]);
                client.setClientName((String) obj[1]);
                client.setClientCity((String) obj[2]);
                client.setClientCarId(null);

                clientDAO.save(client);
            }
        }
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

        final Shop shop = shopDAO.getShopByShopId(shopId);
        final List<Car> cars = shop.getCars();

        cars.add(car);
        shop.setCars(cars);
        shopDAO.save(shop);
    }
}
