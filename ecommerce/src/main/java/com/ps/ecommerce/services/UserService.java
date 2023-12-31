package com.ps.ecommerce.services;


import com.ps.ecommerce.entities.User;
import com.ps.ecommerce.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByPhone(String phone) {
        return userRepository.findOneByPhone(phone);
    }

    public boolean isUserExistByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = getUserByPhone(phone);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid login or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(), mapRolesToAuthorities());
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities() {
        Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
//        mappedAuthorities.add(new SimpleGrantedAuthority("USER"));
//        mappedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
//        mappedAuthorities.add(new SimpleGrantedAuthority("MANAGER"));
//        mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
        return mappedAuthorities;
    }
}
