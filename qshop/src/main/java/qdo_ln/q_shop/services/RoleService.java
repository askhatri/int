package qdo_ln.q_shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qdo_ln.q_shop.entities.Role;
import qdo_ln.q_shop.repositories.RoleRepository;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByName(String name){
        return roleRepository.findOneByName(name);
    }
}
