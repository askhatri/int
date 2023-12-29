package qdo_ln.q_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qdo_ln.q_shop.entities.Order;
import qdo_ln.q_shop.entities.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUser(User user);
}
