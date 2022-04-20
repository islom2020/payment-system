package com.example.paymentsysteminjava.dto.response.agent.payme;

import com.example.paymentsysteminjava.dto.response.agent.BaseAgentResponse;
import com.example.paymentsysteminjava.entity.transaction.TransactionEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JacksonXmlRootElement(localName = "response")
public class PaymeAgentCheckResponse implements BaseAgentResponse {

    @JacksonXmlProperty(localName = "transaction_id")
    private long transactionId;

    @JacksonXmlProperty(localName = "status")
    private int status;

    @JacksonXmlProperty(localName = "message")
    private String message;


    @JsonIgnore
    @Override
    public BaseAgentResponse success(TransactionEntity transaction) {
        return new PaymeAgentCheckResponse(
                transaction.getId(),
                1,
                "success"

        );
    }

    @JsonIgnore
    @Override
    public BaseAgentResponse error(TransactionEntity transaction) {
        return new PaymeAgentCheckResponse(
                transaction.getId(),
                -100,
                "error"

        );
    }
}
