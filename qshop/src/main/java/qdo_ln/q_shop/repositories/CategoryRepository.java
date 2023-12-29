package qdo_ln.q_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import qdo_ln.q_shop.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
