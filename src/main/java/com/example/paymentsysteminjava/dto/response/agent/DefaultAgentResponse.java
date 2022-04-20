package com.example.paymentsysteminjava.dto.response.agent;

import com.example.paymentsysteminjava.entity.agent.AgentEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import com.example.paymentsysteminjava.entity.transaction.TransactionEntity;


public class DefaultAgentResponse implements BaseAgentResponse {
//    @Override
//    public BaseAgentResponse success(AgentEntity agent, MerchantEntity merchantEntity, MerchantServiceEntity merchantServiceEntity, TransactionEntity transaction) {
//        return null;
//    }
//
//    @Override
//    public BaseAgentResponse error(AgentEntity agent, MerchantEntity merchantEntity, MerchantServiceEntity merchantServiceEntity, TransactionEntity transaction) {
//        return null;
//    }
//
    @Override
    public BaseAgentResponse success(TransactionEntity transaction) {
        return null;
    }

    @Override
    public BaseAgentResponse error(TransactionEntity transaction) {
        return null;
    }
}
