package com.example.paymentsysteminjava.dto.response.gateway.payme;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class PaymeCheckTransactioniResponseDto {

    private Result result;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    public static class Result{
        private Timestamp current_time;
        private int transaction;
        private int state;

        private List<Receivers> receivers;
        @JsonIgnoreProperties(ignoreUnknown = true)
        static
        class Receivers{
            private UUID id;
            private BigDecimal amount;
        }
    }

}
