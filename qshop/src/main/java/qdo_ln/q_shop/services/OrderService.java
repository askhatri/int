package qdo_ln.q_shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qdo_ln.q_shop.entities.Order;
import qdo_ln.q_shop.entities.User;
import qdo_ln.q_shop.repositories.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public List<Order> findByUser(User user){
        return orderRepository.findAllByUser(user);
    }

    public Order findById(int id){
        return orderRepository.findById(id).get();
    }
}
