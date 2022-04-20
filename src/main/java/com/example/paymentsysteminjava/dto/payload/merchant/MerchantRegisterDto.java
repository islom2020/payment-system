package com.example.paymentsysteminjava.dto.payload.merchant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MerchantRegisterDto {
    private String username;
    private String password;
    private String name;
    private String secretKey;
}
