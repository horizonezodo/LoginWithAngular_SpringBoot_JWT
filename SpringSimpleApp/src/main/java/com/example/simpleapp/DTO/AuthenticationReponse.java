package com.example.simpleapp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationReponse {
    private String jwt;
}
