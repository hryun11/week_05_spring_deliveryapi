package com.hanghae.week05.model;

import com.hanghae.week05.dto.RestaurantRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.


public class Restaurant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

//    @OneToMany
//    private List<Food> foods;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;


    public Restaurant(RestaurantRequestDto requestDto) {

        this.name = requestDto.getName();
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();

    }


}
