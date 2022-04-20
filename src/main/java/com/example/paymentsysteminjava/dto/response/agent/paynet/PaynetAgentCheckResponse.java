package com.example.paymentsysteminjava.dto.response.agent.paynet;

import com.example.paymentsysteminjava.dto.response.agent.BaseAgentResponse;
import com.example.paymentsysteminjava.entity.transaction.TransactionEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaynetAgentCheckResponse implements BaseAgentResponse {

    @JsonProperty("transaction_id")
    private long transactionId;
    private int status;

    @JsonProperty("service_name")
    private String serviceName;
    @JsonProperty("amount_to_account")
    private BigDecimal amountToAccount;
    private String response;


    @Override
    public BaseAgentResponse success(TransactionEntity transaction) {
        return new PaynetAgentCheckResponse(
                transaction.getId(),
                transaction.getTransactionState(),
                transaction.getOsonServiceEntity().getMerchantServiceEntity().getName(),
                transaction.getTransactionAmountToMerchant(),
                "transaction successfully checked"
        );
    }

    @Override
    public BaseAgentResponse error(TransactionEntity transaction) {
        return new PaynetAgentCheckResponse(
                transaction.getId(),
                transaction.getTransactionState(),
                transaction.getOsonServiceEntity().getMerchantServiceEntity().getName(),
                transaction.getTransactionAmountToMerchant(),
                "transaction check failed"
        );
    }
}
