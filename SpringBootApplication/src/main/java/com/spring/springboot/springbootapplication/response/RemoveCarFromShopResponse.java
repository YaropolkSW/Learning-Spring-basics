package com.spring.springboot.springbootapplication.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class RemoveCarFromShopResponse {
    private final int carId;
    private final int shopId;
    private final String message;
}
