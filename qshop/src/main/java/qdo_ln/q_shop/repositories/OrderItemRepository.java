package qdo_ln.q_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qdo_ln.q_shop.entities.Order;
import qdo_ln.q_shop.entities.OrderItem;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    public List<OrderItem> findAllByOrder(Order order);
}
