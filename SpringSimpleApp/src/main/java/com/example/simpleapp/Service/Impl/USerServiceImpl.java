package com.example.simpleapp.Service.Impl;

import com.example.simpleapp.DTO.SignupRequest;
import com.example.simpleapp.DTO.USerDTO;
import com.example.simpleapp.Service.UserService;
import com.example.simpleapp.model.User;
import com.example.simpleapp.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class USerServiceImpl implements UserService {

    private final UserRepository repo;


    public USerServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public USerDTO createUSer(SignupRequest request) {
        User u = new User();
        u.setEmail(request.getEmail());
        u.setName(request.getName());
        u.setPhone(request.getPhone());
        u.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        User createUser = repo.save(u);
        USerDTO uSerDTO = new USerDTO();
        uSerDTO.setId(createUser.getId());
        uSerDTO.setEmail(createUser.getEmail());
        uSerDTO.setName(createUser.getName());
        uSerDTO.setPhone(createUser.getPhone());
        return uSerDTO;
    }
}
