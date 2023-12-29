package com.ps.ecommerce.repositories;

import com.ps.ecommerce.entities.OrderDetails;
import com.ps.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderDetails, Long> {
    List<OrderDetails> findByUser(User user);

    Optional<OrderDetails> findByIdAndUser(Long orderId, User user);
}
