package com.ps.ecommerce.repositories;

import com.ps.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByQuantityAvailableGreaterThan(int quantity);

    List<Product> findByNameContainingIgnoreCase(String name);
}
