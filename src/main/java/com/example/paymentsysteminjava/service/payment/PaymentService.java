package com.example.paymentsysteminjava.service.payment;

import com.example.paymentsysteminjava.dto.response.agent.BaseAgentResponse;
import com.example.paymentsysteminjava.dto.response.agent.BaseCheckResponse;
import com.example.paymentsysteminjava.entity.agent.AgentDepositEntity;
import com.example.paymentsysteminjava.entity.agent.AgentServiceEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import com.example.paymentsysteminjava.entity.oson.OsonServiceEntity;
import com.example.paymentsysteminjava.entity.transaction.TransactionEntity;
import com.example.paymentsysteminjava.entity.transaction.TransactionState;
import com.example.paymentsysteminjava.exception.DataNotFoundException;
import com.example.paymentsysteminjava.repository.TransactionRepository;
import com.example.paymentsysteminjava.repository.agent.AgentRepository;
import com.example.paymentsysteminjava.repository.agent.AgentServiceRepository;
import com.example.paymentsysteminjava.service.gateway.PaymeTransactionService;
import com.example.paymentsysteminjava.service.gateway.PaynetTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final TransactionRepository transactionRepository;
    private final AgentServiceRepository agentServiceRepository;
    private final AgentRepository agentRepository;
    private final PaymeTransactionService paymeTransactionService;
    private final PaynetTransactionService paynetTransactionService;


    public BaseAgentResponse pay(Long transactionId, String agentUsername){

        Optional<AgentDepositEntity> agentDepositEntity = agentRepository.getAgentDepositByUsername(agentUsername);
        Optional<TransactionEntity> optionalTransaction = transactionRepository.findById(transactionId);

       // TODO: 12.04.2022 give an appropriate name to
        if(optionalTransaction.isEmpty() || agentDepositEntity.isEmpty())
            throw new DataNotFoundException("Bad request");

        TransactionEntity transactionEntity1 = optionalTransaction.get();
        transactionEntity1.setTransactionState(TransactionState.PAYING.getState());
        manageAgentDeposit(transactionEntity1, agentDepositEntity.get(), false);

        //todo Request to Merchant

        TransactionEntity transactionEntity = payRequestToMerchant(transactionEntity1);

        if(transactionEntity.getTransactionState() == TransactionState.PAY_ERROR.getState())
            manageAgentDeposit(transactionEntity1, agentDepositEntity.get(), true);



        return BaseCheckResponse.response(
                transactionEntity.getAgent(),
                transactionEntity.getMerchant(),
                transactionEntity.getOsonServiceEntity().getMerchantServiceEntity(),
                transactionEntity
        );

    }

    private void manageAgentDeposit(TransactionEntity transaction, AgentDepositEntity agentDeposit, boolean isRollback){
        Long serviceId = transaction.getOsonServiceEntity().getId();
        Long agentId = transaction.getAgent().getId();
        Optional<AgentServiceEntity> optionalAgentServiceEntity = agentServiceRepository.findByServiceIdAndAgentId(serviceId, agentId);

        if(optionalAgentServiceEntity.isEmpty())
            throw new DataNotFoundException("Agent Service not found");

        double commission = optionalAgentServiceEntity.get().getCommission();

        if(!isRollback)
            calculate(agentDeposit, transaction.getTransactionAmountFromAgent(), commission);
        else
            calculate(agentDeposit, transaction.getTransactionAmountFromAgent(), (2 * (-1 - commission)));
    }


    private void calculate(
            AgentDepositEntity agentDeposit,
            BigDecimal transactionAmount,
            double commission
            ){

        agentDeposit.setAmount(agentDeposit
                .getAmount()
                .subtract(transactionAmount.multiply(BigDecimal.valueOf(1 + commission)))
        );

        // TODO: 12.04.2022 here commission * transactionAmount should save our budget
    }

    private TransactionEntity payRequestToMerchant
            (
                    TransactionEntity transactionEntity
            ) {
        MerchantEntity merchantEntity = transactionEntity.getMerchant();

        if (merchantEntity.isPayme()) {
            transactionEntity = paymeTransactionService.pay
                    (
                            transactionEntity
                    );
        } else if (merchantEntity.isPaynet()) {
            transactionEntity = paynetTransactionService.pay
                    (
                            transactionEntity
                    );
        }
        transactionRepository.save(transactionEntity);

        return transactionEntity;
    }


}