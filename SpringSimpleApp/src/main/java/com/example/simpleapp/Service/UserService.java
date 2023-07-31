package com.example.simpleapp.Service;

import com.example.simpleapp.DTO.SignupRequest;
import com.example.simpleapp.DTO.USerDTO;

public interface UserService {
    USerDTO createUSer(SignupRequest request);
}
