package com.example.paymentsysteminjava.dto.payload.agent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgentServiceDto {
    private double commission;
    private long agentId;
    private long serviceId;
}
