package com.example.paymentsysteminjava.dto.payload.merchant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MerchantTransactionCheckDto {
    private String current_time;
    private int transaction;
    private int state;
}
