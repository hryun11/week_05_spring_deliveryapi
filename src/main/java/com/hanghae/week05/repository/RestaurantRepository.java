package com.hanghae.week05.repository;

import com.hanghae.week05.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findAll();

    Restaurant findByName(String name);
}
