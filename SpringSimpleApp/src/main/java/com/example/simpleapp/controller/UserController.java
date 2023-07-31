package com.example.simpleapp.controller;

import com.example.simpleapp.Configuration.untils.JWTUnitls;
import com.example.simpleapp.DTO.HelloResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    private final JWTUnitls jwt;

    private final UserDetailsService userDetailsService;

    public UserController(JWTUnitls jwt, UserDetailsService userDetailsService) {
        this.jwt = jwt;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/hello")
    public HelloResponse hello(){
        return new HelloResponse("Hello");
    }
}
