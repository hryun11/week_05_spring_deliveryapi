package com.hanghae.week05.dto.Orders.response;

import com.hanghae.week05.model.Orders;
import lombok.Getter;

import java.util.List;

@Getter
public class OrdersResponseDto {
    private String restaurantName;
    private List<FoodsResponseDto> foods;
    private int deliveryFee;
    private int totalPrice;


    public OrdersResponseDto(Orders orders, int deliveryFee, List<FoodsResponseDto> foods) {
        this.restaurantName = orders.getRestaurantName();
        this.foods = foods;
        this.deliveryFee = deliveryFee;
        this.totalPrice = orders.getTotalPrice();
    }
}
