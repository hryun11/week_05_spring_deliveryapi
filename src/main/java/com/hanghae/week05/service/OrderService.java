package com.hanghae.week05.service;

import com.hanghae.week05.dto.Orders.request.OrdersRequestDto;
import com.hanghae.week05.dto.Orders.response.FoodsResponseDto;
import com.hanghae.week05.dto.Orders.response.OrdersResponseDto;
import com.hanghae.week05.model.Food;
import com.hanghae.week05.model.OrderItem;
import com.hanghae.week05.model.Orders;
import com.hanghae.week05.model.Restaurant;
import com.hanghae.week05.repository.FoodRepository;
import com.hanghae.week05.repository.OrderItemRepository;
import com.hanghae.week05.repository.OrderRepository;
import com.hanghae.week05.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public OrdersResponseDto order(OrdersRequestDto ordersRequestDto) {
        Restaurant restaurant = getRestaurant(ordersRequestDto);

        int totalPrice = 0;
        List<FoodsResponseDto> foodsResponseDtoList = new ArrayList<>();
        List<OrderItem> orderItems = ordersRequestDto.getFoods();
        List<OrderItem> orderItemList = new ArrayList<>();
        for (OrderItem tempOrderItem : orderItems) {

            int quantity = tempOrderItem.getQuantity();
            if (quantity < 1 || quantity > 100) {
                throw new IllegalArgumentException("ILLEGAL_FOOD_ORDER_QUANTITY");
            }

            Food food = getFood(tempOrderItem);

            OrderItem orderItem = OrderItem.builder()
                    .quantity(tempOrderItem.getQuantity())
                    .name(food.getName())
                    .price(food.getPrice() * quantity)
                    .food(food)
                    .build();
            orderItemRepository.save(orderItem);
            FoodsResponseDto foodsResponseDto = new FoodsResponseDto(orderItem);
            foodsResponseDtoList.add(foodsResponseDto);
            totalPrice += food.getPrice() * quantity;
            orderItemList.add(orderItem);
        }

        if (totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("ILLEGAL_TOTAL_PRICE");
        }

        int deliveryFee = restaurant.getDeliveryFee();
        totalPrice += deliveryFee;
        Orders orders = Orders.builder()
                .restaurantName(restaurant.getName())
                .totalPrice(totalPrice)
                .foods(orderItemList)
                .build();
        orderRepository.save(orders);
        OrdersResponseDto ordersResponseDto = new OrdersResponseDto(orders, deliveryFee, foodsResponseDtoList);
        return ordersResponseDto;
    }


    private Restaurant getRestaurant(OrdersRequestDto ordersRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(ordersRequestDto.getRestaurantId())
                .orElseThrow(
                        () -> new NullPointerException("RESTAURANT_IS_NULL")
                );
        return restaurant;
    }

    private Food getFood(OrderItem tempOrderItem) {
        return foodRepository.findById(tempOrderItem.getId())
                .orElseThrow(() -> new NullPointerException("CANT_FIND_FOOD"));
    }

    @Transactional
    public List<OrdersResponseDto> findAllOrder() {
        List<OrdersResponseDto> ordersResponseDtoList = new ArrayList<>();

        List<Orders> ordersList = orderRepository.findAll();

        for (Orders orders : ordersList) {
            int deliveryFee = restaurantRepository.findByName(orders.getRestaurantName()).getDeliveryFee();
            List<FoodsResponseDto> foodsResponseDtoList = new ArrayList<>();


            List<OrderItem> orderItemsList  = orderItemRepository.findOrderItemsByOrders(orders);
            for (OrderItem orderItem : orderItemsList) {
                FoodsResponseDto foodsResponseDto = new FoodsResponseDto(orderItem);
                foodsResponseDtoList.add(foodsResponseDto);
            }

            OrdersResponseDto ordersResponseDto = new OrdersResponseDto(orders, deliveryFee, foodsResponseDtoList);
            ordersResponseDtoList.add(ordersResponseDto);
        }

        return ordersResponseDtoList;
    }
}
