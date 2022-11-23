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
    private int id;

    private String shopName;

    public static ShopDTO of(final Shop shop) {
        return ShopDTO.builder()
                .id(shop.getId())
                .shopName(shop.getShopName())
                .build();
    }

}
