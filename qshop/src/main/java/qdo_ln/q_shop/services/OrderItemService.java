package qdo_ln.q_shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qdo_ln.q_shop.entities.Order;
import qdo_ln.q_shop.entities.OrderItem;
import qdo_ln.q_shop.repositories.OrderItemRepository;

import java.util.List;

@Service
public class OrderItemService {
    private OrderItemRepository orderItemRepository;

    @Autowired
    public void setOrderItemRepository(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public List<OrderItem> findByOrder(Order order){
        return orderItemRepository.findAllByOrder(order);
    }
}
