package com.hanghae.week05.dto;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor



public class RestaurantRequestDto {

    private String name;
    private int minOrderPrice;
    private int deliveryFee;

}
