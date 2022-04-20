package com.example.paymentsysteminjava.dto.response.agent;

import com.example.paymentsysteminjava.entity.agent.AgentEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import com.example.paymentsysteminjava.entity.transaction.TransactionEntity;
import com.example.paymentsysteminjava.entity.transaction.TransactionState;

public interface BaseAgentResponse {
    default BaseAgentResponse response(
            AgentEntity agent,
            MerchantEntity merchantEntity,
            MerchantServiceEntity merchantServiceEntity,
            TransactionEntity transaction
    ) {
        if (transaction.getTransactionState() == TransactionState.CHECKED.getState()) {
//            return success(agent, merchantEntity, merchantServiceEntity, transaction);
            return success(transaction);
        } else {
            return error(transaction);
        }
    }

//    BaseAgentResponse success(AgentEntity agent,
//                              MerchantEntity merchantEntity,
//                              MerchantServiceEntity merchantServiceEntity,
//                              TransactionEntity transaction);

    BaseAgentResponse success(TransactionEntity transaction);

//    BaseAgentResponse error(AgentEntity agent,
//                            MerchantEntity merchantEntity,
//                            MerchantServiceEntity merchantServiceEntity,
//                            TransactionEntity transaction);

    BaseAgentResponse error(TransactionEntity transaction);
}
