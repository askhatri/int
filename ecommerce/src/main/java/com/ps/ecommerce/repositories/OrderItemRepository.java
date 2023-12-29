package com.ps.ecommerce.repositories;

import com.ps.ecommerce.entities.Order;
import com.ps.ecommerce.entities.OrderItem;
import com.ps.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);

    Optional<OrderItem> findByOrderAndProduct(Order order, Product product);
}
