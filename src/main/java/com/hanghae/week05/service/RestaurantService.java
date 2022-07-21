package com.hanghae.week05.service;

import com.hanghae.week05.dto.RestaurantRequestDto;
import com.hanghae.week05.model.Restaurant;
import com.hanghae.week05.repository.RestaurantRepository;
import jdk.javadoc.internal.doclets.formats.html.Contents;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    //  음식점 등록
    public Restaurant registerRestaurant(RestaurantRequestDto requestDto) {

        int minOrderPrice = requestDto.getMinOrderPrice();
        int deliveryFee = requestDto.getDeliveryFee();

        // 1. 허용값: 1,000원 ~ 100,000원 입력
        if (minOrderPrice < 1000 || minOrderPrice > 100000) {
            throw new IllegalArgumentException("최소 주문 금액은 1,000원 ~ 100,000원 입니다.");
        }

        // 2. 100 원 단위로만 입력 가능 (예. 2,220원 입력 시 에러발생. 2,300원은 입력 가능)
        if (minOrderPrice % 100 != 0) {
            throw new IllegalArgumentException("최소 주문 금액은 100원 단위로 설정해야합니다.");
        }

        // 1. 허용값: 0원 ~ 10,000원 (예. 11,000원 입력 시 에러발생.)
        if (deliveryFee < 0 || deliveryFee > 10000) {
            throw new IllegalArgumentException("배달비는 0원 ~ 10000원 입니다.");
        }

        // 2. 500 원 단위로만 입력 가능 (예. 2,200원 입력 시 에러발생. 2,500원 입력 가능)
        if (deliveryFee % 500 != 0) {
            throw new IllegalArgumentException("배달비는 500원 단위로 설정해야합니다.");
        }

        Restaurant restaurant = new Restaurant(requestDto);
        return restaurantRepository.save(restaurant);


    }
}
