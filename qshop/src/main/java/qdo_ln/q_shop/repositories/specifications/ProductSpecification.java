package qdo_ln.q_shop.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import qdo_ln.q_shop.entities.Product;

public class ProductSpecification {
    public static Specification<Product> priceGE(double value){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), value);
    }

    public static Specification<Product> priceLE(double value){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), value);
    }

    public static Specification<Product> categoryEq(int value){
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("category").get("id"), value);
    }
}
