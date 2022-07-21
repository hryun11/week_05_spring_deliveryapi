package com.hanghae.week05.dto.Orders.request;

import com.hanghae.week05.model.OrderItem;
import lombok.Getter;

import java.util.List;

@Getter
public class OrdersRequestDto {
    private Long restaurantId;
    private List<OrderItem> foods;
}
