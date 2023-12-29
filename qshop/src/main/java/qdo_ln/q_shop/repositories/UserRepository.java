package qdo_ln.q_shop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import qdo_ln.q_shop.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findOneByPhone(String phone);
    boolean existsByPhone(String phone);
}
