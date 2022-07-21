package com.hanghae.week05.service;

import com.hanghae.week05.dto.FoodRequestDto;
import com.hanghae.week05.model.Food;
import com.hanghae.week05.model.Restaurant;
import com.hanghae.week05.repository.FoodRepository;
import com.hanghae.week05.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;






@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void registerFoods(Long restaurantId, List<FoodRequestDto> requestDtoList) {

        Optional<Restaurant> foundRestaurant = restaurantRepository.findById(restaurantId);
        // restuarantRepository에서 restaurantId로 음식점 찾는 것을 foundRestaurant.

        checkRestaurant(foundRestaurant);
        // 찾은 음식점이 존재하는지 확인
        // 존재하지 않는다면 예외처리
        Restaurant restaurant = foundRestaurant.get();
        // 찾은 음식점에 값이 존재하면 반환

        for (FoodRequestDto requestDto : requestDtoList) {
            String foodName = requestDto.getName();
            int foodPrice = requestDto.getPrice();

            checkDuplicateRestaurantFood(restaurant, foodName);

            checkFoodPrice(foodPrice);

            Food food = Food.builder()
                    .name(foodName)
                    .price(foodPrice)
                    .restaurant(restaurant)
                    .build();

            foodRepository.save(food);
        }
    }


    private void checkRestaurant(Optional<Restaurant> foundRestaurant) {
        if (!foundRestaurant.isPresent())
            throw new IllegalArgumentException("음식점이 존재하지 않습니다.");
    }

    private void checkDuplicateRestaurantFood(Restaurant restaurant, String foodName) {
        Optional<Food> found = foodRepository.findFoodByRestaurantAndName(restaurant, foodName);
        if(found.isPresent())
            throw new IllegalArgumentException("메뉴가 존재합니다");
    }

    private void checkFoodPrice(int foodPrice) {
        if (foodPrice < 100)
            throw new IllegalArgumentException("음식 가격은 100원 이상입니다.");

        if (foodPrice > 1_000_000)
            throw new IllegalArgumentException("음식 가격은 1,000,000원 이하입니다.");

        if (foodPrice % 100 > 0)
            throw new IllegalArgumentException("음식 가격은 100원 단위입니다.");
    }


    // 음식점 메뉴판 조회
    @Transactional
    public List<Food> findAllRestaurantFoods(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(
                        () -> new NullPointerException("음식점이 없습니다."));

        return foodRepository.findFoodsByRestaurant(restaurant);
    }
}