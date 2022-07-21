package com.hanghae.week05.model;

import com.hanghae.week05.dto.FoodRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Getter
//@NoArgsConstructor // 기본생성자를 대신 생성해줍니다.
//@Entity // 테이블임을 나타냅니다.
//public class Food {
//
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
//    private Restaurant restaurant;
//
//    @Column(nullable = false)
//    private String name;
//
//    @Column(nullable = false)
//    private int price;
//
//
//    public Food(Restaurant restaurant, FoodRequestDto requestDto) {
//
//        this.restaurant = restaurant;
//        this.name = requestDto.getName();
//        this.price = requestDto.getPrice();
//    }
//}



@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @ManyToOne
    @JoinColumn(name="restaurant_id", nullable = false)
    private Restaurant restaurant;
}