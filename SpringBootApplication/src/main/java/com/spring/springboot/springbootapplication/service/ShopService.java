package com.spring.springboot.springbootapplication.service;

import com.spring.springboot.springbootapplication.dao.ShopDAO;
import com.spring.springboot.springbootapplication.dto.ShopDTO;
import com.spring.springboot.springbootapplication.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {

    @Autowired
    private ShopDAO shopDAO;

    public List<ShopDTO> getAllShops() {
        final List<Shop> shops = shopDAO.findAll();
        final List<ShopDTO> shopsDTO = new ArrayList<>();

        for (final Shop shop : shops) {
            final ShopDTO shopDTO = new ShopDTO();

            shopDTO.setShopId(shop.getShopId());
            shopDTO.setShopName(shop.getShopName());

            shopsDTO.add(shopDTO);
        }

        return shopsDTO;
    }

    public ShopDTO getShopById(final int id) {
        final Shop shop = shopDAO.getShopByShopId(id);
        final ShopDTO shopDTO = new ShopDTO();

        shopDTO.setShopId(shop.getShopId());
        shopDTO.setShopName(shop.getShopName());

        return shopDTO;
    }
}
