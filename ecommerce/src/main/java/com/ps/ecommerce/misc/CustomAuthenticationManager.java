package com.ps.ecommerce.misc;

import java.util.List;

public class CustomAuthenticationManager extends ProviderManager {

    public CustomAuthenticationManager(List<AuthenticationProvider> providers) {
        super(providers);
    }
}
