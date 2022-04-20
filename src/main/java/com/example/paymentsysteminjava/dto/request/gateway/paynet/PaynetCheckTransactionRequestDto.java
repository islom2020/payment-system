package com.example.paymentsysteminjava.dto.request.gateway.paynet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaynetCheckTransactionRequestDto {

    private Long id;
    private Timestamp timestamp;
    private String account;
    private BigDecimal amount;

    private final String method = "CreateTransaction";
    private final PaynetCheckTransactionRequestDto.Params params = new PaynetCheckTransactionRequestDto.Params(id, timestamp, account, amount);

    public PaynetCheckTransactionRequestDto(
            String id,
            Timestamp timestamp,
            String transactionAccount,
            BigDecimal transactionAmountToMerchant) {

        this.id = Long.parseLong(id);
        this.timestamp = timestamp;
        this.account = transactionAccount;
        this.amount = transactionAmountToMerchant;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class Params{
        private Long id;
        private Timestamp timestamp;
        private PaynetCheckTransactionRequestDto.Params.Account account;
        private BigDecimal amount;
        private int state;

        Params(Long id, Timestamp timestamp, String account, BigDecimal amount) {
            this.id = id;
            this.timestamp = timestamp;
            this.account = new PaynetCheckTransactionRequestDto.Params.Account(account);
            this.amount = amount;
        }

        static class Account{
            private String account;

            Account(String account) {
                this.account = account;
            }
        }
    }

}
