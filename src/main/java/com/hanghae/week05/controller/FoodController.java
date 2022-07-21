package com.hanghae.week05.controller;

import com.hanghae.week05.dto.FoodRequestDto;
import com.hanghae.week05.model.Food;
import com.hanghae.week05.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;


    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFoods(@PathVariable Long restaurantId, @RequestBody List<FoodRequestDto> requestDtoList) {
        foodService.registerFoods(restaurantId, requestDtoList);
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> findAllRestaurantFoods(@PathVariable Long restaurantId) {
        return foodService.findAllRestaurantFoods(restaurantId);
    }
}