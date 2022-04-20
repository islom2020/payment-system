package com.example.paymentsysteminjava.dto.request.agent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class DefaultAgentRequest extends BaseAgentRequest {
    private String account;
    private BigDecimal amount;
    private Long serviceId;

    @Override
    public DefaultAgentRequest getDefaultAgentRequest() {
        return this;
    }
}
