package com.example.paymentsysteminjava.service.agent;

import com.example.paymentsysteminjava.dto.payload.agent.AgentServiceDto;
import com.example.paymentsysteminjava.dto.response.BaseApiResponse;

public interface AgentServiceService {
    BaseApiResponse add(AgentServiceDto agentServiceDto);
}
