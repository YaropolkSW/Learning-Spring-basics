package com.spring.springboot.springbootapplication.service;

import com.spring.springboot.springbootapplication.dao.ShopDAO;
import com.spring.springboot.springbootapplication.dto.ShopDTO;
import com.spring.springboot.springbootapplication.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopService {

    private static final String NO_SUCH_SHOP_EXC_MESSAGE = "There is no shop with Id = %s";

    private final ShopDAO shopDAO;

    @Autowired
    public ShopService(
            final ShopDAO shopDAO
    ) {
        this.shopDAO = shopDAO;
    }

    public List<ShopDTO> getAllShops() {
        final List<ShopDTO> shopsDTO = shopDAO
                .findAll().stream()
                .map(ShopDTO::of)
                .collect(Collectors.toList());

        return shopsDTO;
    }

    public ShopDTO getShopById(final int shopId) {
        final Shop shop = shopDAO
                .findById(shopId)
                .orElseThrow(() -> new EntityNotFoundException(String.format(NO_SUCH_SHOP_EXC_MESSAGE, shopId)));
        final ShopDTO shopDTO = ShopDTO.of(shop);

        return shopDTO;
    }
}
