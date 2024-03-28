package com.julian5335.spring.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user \"" + username + "\" not found"));
        System.out.println(user);
        return user;
    }

    public User add() {
        User user = new User();
        user.setUsername("Julian");
        String password = "j@123";
        password = passwordEncoder.encode(password);
        user.setPassword(password);
        return repository.save(user);
    }
    
}
