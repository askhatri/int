package com.ps.ecommerce.services;

import com.ps.ecommerce.entities.OrderDetails;
import com.ps.ecommerce.entities.OrderItem;
import com.ps.ecommerce.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private OrderItemRepository orderItemRepository;

    @Autowired
    public void setOrderItemRepository(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> findByOrder(OrderDetails order) {
        return orderItemRepository.findAllByOrder(order);
    }
}
