package com.ps.ecommerce.repositories;

import com.ps.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhone(String phone);

    User findOneByPhone(String phone);

    boolean existsByPhone(String phone);
}
