package com.hanghae.week05.repository;

import com.hanghae.week05.model.OrderItem;
import com.hanghae.week05.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findOrderItemsByOrders(Orders orders);
}