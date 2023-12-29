package com.ps.ecommerce;

import com.ps.ecommerce.misc.AuthenticationManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager() {
        return new AuthenticationManager();
    }
}