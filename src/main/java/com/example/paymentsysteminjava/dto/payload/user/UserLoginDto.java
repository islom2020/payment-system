package com.example.paymentsysteminjava.dto.payload.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {
    private String username;
    private String password;
}
