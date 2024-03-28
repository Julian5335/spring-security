package com.julian5335.spring.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AdminRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("admin \"" + username + "\" not found"));
        System.out.println(admin);
        return admin;
    }

    public Admin add() {
        Admin admin = new Admin();
        admin.setEmail("Arnold");
        String password = "a@123";
        password = passwordEncoder.encode(password);
        admin.setPassword(password);
        return repository.save(admin);
    }
    
}
