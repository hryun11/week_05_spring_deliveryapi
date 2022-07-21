package com.hanghae.week05.repository;

import com.hanghae.week05.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {
}