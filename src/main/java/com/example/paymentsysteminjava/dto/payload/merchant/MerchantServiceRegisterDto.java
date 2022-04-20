package com.example.paymentsysteminjava.dto.payload.merchant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MerchantServiceRegisterDto {
    private String name;
    private Long merchantId;
    private Long merchantServiceId;
}
