package qdo_ln.q_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import qdo_ln.q_shop.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product, Integer>, JpaSpecificationExecutor<Product> {
}
