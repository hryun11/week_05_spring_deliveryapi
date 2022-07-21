package com.hanghae.week05.repository;

import com.hanghae.week05.model.Food;
import com.hanghae.week05.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findFoodsByRestaurant(Restaurant restaurant);
    Optional<Food> findFoodByRestaurantAndName(Restaurant restaurant, String foodName);
}