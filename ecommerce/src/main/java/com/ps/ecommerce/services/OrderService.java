package com.ps.ecommerce.services;

import com.ps.ecommerce.entities.OrderDetails;
import com.ps.ecommerce.entities.User;
import com.ps.ecommerce.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDetails save(OrderDetails order) {
        return orderRepository.save(order);
    }

    public List<OrderDetails> findByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    public OrderDetails findById(long id) {
        return orderRepository.findById(id).get();
    }
}
