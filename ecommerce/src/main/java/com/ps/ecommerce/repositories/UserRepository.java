package com.ps.ecommerce.repositories;

import com.ps.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findOneByUsername(String username);

    boolean existsByUsername(String username);
}
