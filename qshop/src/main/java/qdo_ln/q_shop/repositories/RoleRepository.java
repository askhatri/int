package qdo_ln.q_shop.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import qdo_ln.q_shop.entities.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findOneByName(String name);
}
