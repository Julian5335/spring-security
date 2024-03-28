package com.julian5335.spring.security.security;

import org.springframework.security.core.GrantedAuthority;

public class UserAuthority implements GrantedAuthority {

    @Override
    public String getAuthority() {
        return "USER";
    }
    
}
