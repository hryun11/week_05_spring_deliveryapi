package com.hanghae.week05.controller;

import com.hanghae.week05.dto.RestaurantRequestDto;
import com.hanghae.week05.model.Restaurant;
import com.hanghae.week05.repository.RestaurantRepository;
import com.hanghae.week05.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;

    private final RestaurantService restaurantService;


    // 전체 음식점 리스트 조회
    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepository.findAll();
    }


    // 음식점 등록
    @PostMapping("/restaurant/register")
    public Restaurant registerRestaurant(@RequestBody RestaurantRequestDto requestDto) {
        return restaurantService.registerRestaurant(requestDto);
    }


}
