package com.example.paymentsysteminjava.dto.response.agent;

import com.example.paymentsysteminjava.dto.response.agent.paynet.PaynetAgentCheckResponse;
import com.example.paymentsysteminjava.entity.agent.AgentEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import com.example.paymentsysteminjava.entity.transaction.TransactionEntity;

public interface BaseCheckResponse {
    static BaseAgentResponse response(
            AgentEntity agent,
            MerchantEntity merchantEntity,
            MerchantServiceEntity merchantServiceEntity,
            TransactionEntity transaction
    ) {
        BaseAgentResponse baseAgentResponse;
        if (agent.isPaynet()) {
            baseAgentResponse = new PaynetAgentCheckResponse();
        } else {
            baseAgentResponse = new DefaultAgentResponse();
        }
        return baseAgentResponse.response(agent, merchantEntity, merchantServiceEntity, transaction);
    }
}
