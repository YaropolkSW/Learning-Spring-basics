package com.spring.springboot.springbootapplication.dto;

import com.spring.springboot.springbootapplication.entity.Shop;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ShopDTO {
    private int shopId;

    private String shopName;

    public static ShopDTO of(final Shop shop) {
        final ShopDTO shopDTO = ShopDTO.builder()
                .shopId(shop.getShopId())
                .shopName(shop.getShopName())
                .build();

        return shopDTO;
    }

}
