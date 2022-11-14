package com.spring.springboot.springbootapplication.dto;

import com.spring.springboot.springbootapplication.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopDTO {
    private int shopId;

    private String shopName;

    public static ShopDTO of(final Shop shop) {
        final ShopDTO shopDTO = new ShopDTO();

        shopDTO.setShopId(shop.getShopId());
        shopDTO.setShopName(shop.getShopName());

        return shopDTO;
    }

}
