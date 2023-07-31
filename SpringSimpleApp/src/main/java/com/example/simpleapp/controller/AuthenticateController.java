package com.example.simpleapp.controller;

import com.example.simpleapp.Configuration.untils.JWTUnitls;
import com.example.simpleapp.DTO.AuthenticationReponse;
import com.example.simpleapp.DTO.AuthenticationRequest;
import com.example.simpleapp.jwt.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

    private final AuthenticationManager manager;

    private final UserDetailsServiceImpl userDetailsService;

    private final JWTUnitls jwt;

    public AuthenticateController(AuthenticationManager manager, UserDetailsServiceImpl userDetailsService, JWTUnitls jwt) {
        this.manager = manager;
        this.userDetailsService = userDetailsService;
        this.jwt = jwt;
    }
    @PostMapping("/authentication")
    public AuthenticationReponse createAuthenticationToken(@RequestBody AuthenticationRequest request, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
        try{
            manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Invalid Username or Password");
        } catch (DisabledException d){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User name is not create, please Register first");
            return null;
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        final String jwt1= jwt.generateToken(userDetails.getUsername());
        return new AuthenticationReponse(jwt1);
    }
}
