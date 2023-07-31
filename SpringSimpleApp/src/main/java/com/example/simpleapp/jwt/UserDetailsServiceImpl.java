package com.example.simpleapp.jwt;

import com.example.simpleapp.model.User;
import com.example.simpleapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repo;

    public UserDetailsServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = repo.findByEmail(email);
        if(u == null) {
            throw new UsernameNotFoundException("user name not found", null);
        }
        return new org.springframework.security.core.userdetails.User(u.getEmail(),u.getPassword(), new ArrayList<>());
    }
}
