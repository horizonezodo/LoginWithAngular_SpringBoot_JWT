package com.example.simpleapp.controller;

import com.example.simpleapp.DTO.SignupRequest;
import com.example.simpleapp.DTO.USerDTO;
import com.example.simpleapp.Service.Impl.USerServiceImpl;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class SiginController {

    private final USerServiceImpl service;

    public SiginController(USerServiceImpl service) {
        this.service = service;
    }
    @PostMapping("/register")
    public ResponseEntity<?> createUSer(@RequestBody SignupRequest request){
        USerDTO createUser = service.createUSer(request);
        if(createUser == null){
            return new ResponseEntity<>("Error, Can not create user", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createUser, HttpStatus.OK);
    }
}
