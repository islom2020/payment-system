package com.example.paymentsysteminjava.service.transaction;

import com.example.paymentsysteminjava.dto.request.agent.DefaultAgentRequest;
import com.example.paymentsysteminjava.dto.response.agent.BaseAgentResponse;
import com.example.paymentsysteminjava.dto.response.agent.BaseCheckResponse;
import com.example.paymentsysteminjava.entity.agent.AgentEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import com.example.paymentsysteminjava.entity.oson.OsonServiceEntity;
import com.example.paymentsysteminjava.entity.transaction.TransactionEntity;
import com.example.paymentsysteminjava.entity.transaction.TransactionState;
import com.example.paymentsysteminjava.repository.agent.AgentRepository;
import com.example.paymentsysteminjava.repository.OsonRepository;
import com.example.paymentsysteminjava.repository.TransactionRepository;
import com.example.paymentsysteminjava.service.gateway.PaymeTransactionService;
import com.example.paymentsysteminjava.service.gateway.PaynetTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CheckTransactionService {
    private final OsonRepository osonRepository;
//    private final MerchantRepository merchantRepository;
    private final PaymeTransactionService paymeTransactionService;
    private final PaynetTransactionService paynetTransactionService;
    private final TransactionRepository transactionRepository;
    private final AgentRepository agentRepository;


    public BaseAgentResponse check(
            DefaultAgentRequest defaultAgentRequest,
            String username) {

        AgentEntity agentEntity = agentRepository.findByUsername(username);
        //// TODO: 11.04.2022 check agent validity
        if (agentEntity == null || !agentEntity.isActive())
            throw new UsernameNotFoundException("Agent not found");

        OsonServiceEntity osonServiceEntity = osonRepository.findById(defaultAgentRequest.getServiceId()).get();

        TransactionEntity transactionEntity = new TransactionEntity();

        transactionEntity.setTransactionState(TransactionState.CREATED.getState());
        transactionEntity.setTransactionAccount(defaultAgentRequest.getAccount());
        transactionEntity.setTransactionAmountFromAgent(defaultAgentRequest.getAmount());
        transactionEntity.setTransactionAmountToMerchant(
                getMerchantAmount(defaultAgentRequest.getAmount(), agentEntity)
        );
        transactionEntity.setAgent(agentEntity);
        transactionEntity.setOsonServiceEntity(osonServiceEntity);


        MerchantServiceEntity merchantServiceEntity = osonServiceEntity.getMerchantServiceEntity();
        MerchantEntity merchantEntity = merchantServiceEntity.getMerchantEntity();

        transactionEntity.setMerchant(merchantEntity);

        transactionRepository.save(transactionEntity);

        TransactionEntity transaction = requestToMerchant(
                merchantServiceEntity,
                merchantEntity,
                transactionEntity);

        transactionRepository.save(transaction);

        return BaseCheckResponse.response(
                agentEntity,
                merchantEntity,
                merchantServiceEntity,
                transactionEntity
        );
    }


    private TransactionEntity requestToMerchant
            (
                    MerchantServiceEntity merchantServiceEntity,
                    MerchantEntity merchantEntity,
                    TransactionEntity transactionEntity
            ) {

        if (merchantEntity.isPayme()) {
            transactionEntity = paymeTransactionService.createTransaction
                    (
                            transactionEntity,
                            merchantServiceEntity
                    );
        } else if (merchantEntity.isClick()) {
            //TODO
        } else if (merchantEntity.isPaynet()) {
//           transactionEntity = paynetTransactionService.(
//                    transactionEntity
//            );
        }

        return transactionEntity;
    }


    private BigDecimal getMerchantAmount(BigDecimal amount, AgentEntity agentEntity) {
        return amount.subtract(amount.multiply(BigDecimal.valueOf(0.1)));
    }
}
