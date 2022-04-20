package com.example.paymentsysteminjava.service.payment;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.paymentsysteminjava.entity.agent.AgentDepositEntity;
import com.example.paymentsysteminjava.entity.agent.AgentEntity;
import com.example.paymentsysteminjava.entity.agent.AgentServiceEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import com.example.paymentsysteminjava.entity.oson.OsonServiceEntity;
import com.example.paymentsysteminjava.entity.transaction.TransactionEntity;
import com.example.paymentsysteminjava.exception.DataNotFoundException;
import com.example.paymentsysteminjava.repository.TransactionRepository;
import com.example.paymentsysteminjava.repository.agent.AgentRepository;
import com.example.paymentsysteminjava.repository.agent.AgentServiceRepository;
import com.example.paymentsysteminjava.service.gateway.PaymeTransactionService;
import com.example.paymentsysteminjava.service.gateway.PaynetTransactionService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PaymentService.class})
@ExtendWith(SpringExtension.class)
class PaymentServiceTest {
    @MockBean
    private AgentRepository agentRepository;

    @MockBean
    private AgentServiceRepository agentServiceRepository;

    @Autowired
    private PaymentService paymentService;

    @MockBean
    private TransactionRepository transactionRepository;

    /**
     * Method under test: {@link PaymentService#pay(Long, String)}
     */
    @Test
    void testPay() {
        AgentEntity agentEntity = new AgentEntity();
        agentEntity.setActive(true);
        agentEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        agentEntity.setCreatedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        agentEntity.setId(123L);
        agentEntity.setIsApelsin(true);
        agentEntity.setIsClick(true);
        agentEntity.setIsPayme(true);
        agentEntity.setIsPaynet(true);
        agentEntity.setName("Name");
        agentEntity.setPassword("iloveyou");
        agentEntity.setPermission("Permission");
        agentEntity.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agentEntity.setUpdatedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        agentEntity.setUsername("janedoe");

        MerchantEntity merchantEntity = new MerchantEntity();
        merchantEntity.setActive(true);
        merchantEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        merchantEntity.setCreatedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        merchantEntity.setId(123L);
        merchantEntity.setName("Name");
        merchantEntity.setPassword("iloveyou");
        merchantEntity.setPayme(true);
        merchantEntity.setSecretKey("EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY");
        merchantEntity.setUcell(true);
        merchantEntity.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        merchantEntity.setUpdatedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        merchantEntity.setUsername("janedoe");
        merchantEntity.setYandex(true);

        MerchantEntity merchantEntity1 = new MerchantEntity();
        merchantEntity1.setActive(true);
        merchantEntity1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        merchantEntity1.setCreatedDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        merchantEntity1.setId(123L);
        merchantEntity1.setName("Name");
        merchantEntity1.setPassword("iloveyou");
        merchantEntity1.setPayme(true);
        merchantEntity1.setSecretKey("EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY");
        merchantEntity1.setUcell(true);
        merchantEntity1.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        merchantEntity1.setUpdatedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        merchantEntity1.setUsername("janedoe");
        merchantEntity1.setYandex(true);

        MerchantServiceEntity merchantServiceEntity = new MerchantServiceEntity();
        merchantServiceEntity.setActive(true);
        merchantServiceEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        merchantServiceEntity.setCreatedDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        merchantServiceEntity.setId(123L);
        merchantServiceEntity.setMerchantEntity(merchantEntity1);
        merchantServiceEntity.setMerchantServiceId(123L);
        merchantServiceEntity.setName("Name");
        merchantServiceEntity.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        merchantServiceEntity.setUpdatedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));

        OsonServiceEntity osonServiceEntity = new OsonServiceEntity();
        osonServiceEntity.setActive(true);
        osonServiceEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        osonServiceEntity.setCreatedDate(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        osonServiceEntity.setId(123L);
        osonServiceEntity.setMerchantServiceEntity(merchantServiceEntity);
        osonServiceEntity.setName("Name");
        osonServiceEntity.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        osonServiceEntity.setUpdatedDate(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setActive(true);
        transactionEntity.setAgent(agentEntity);
        transactionEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        transactionEntity.setCreatedDate(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        transactionEntity.setId(123L);
        transactionEntity.setMerchant(merchantEntity);
        transactionEntity.setOsonServiceEntity(osonServiceEntity);
        transactionEntity.setTransactionAccount("3");
        transactionEntity.setTransactionAmountFromAgent(BigDecimal.valueOf(42L));
        transactionEntity.setTransactionAmountToMerchant(BigDecimal.valueOf(42L));
        transactionEntity.setTransactionState(1);
        transactionEntity.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        transactionEntity.setUpdatedDate(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<TransactionEntity> ofResult = Optional.of(transactionEntity);

        AgentEntity agentEntity1 = new AgentEntity();
        agentEntity1.setActive(true);
        agentEntity1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agentEntity1.setCreatedDate(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        agentEntity1.setId(123L);
        agentEntity1.setIsApelsin(true);
        agentEntity1.setIsClick(true);
        agentEntity1.setIsPayme(true);
        agentEntity1.setIsPaynet(true);
        agentEntity1.setName("Name");
        agentEntity1.setPassword("iloveyou");
        agentEntity1.setPermission("Permission");
        agentEntity1.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agentEntity1.setUpdatedDate(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        agentEntity1.setUsername("janedoe");

        MerchantEntity merchantEntity2 = new MerchantEntity();
        merchantEntity2.setActive(true);
        merchantEntity2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        merchantEntity2.setCreatedDate(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        merchantEntity2.setId(123L);
        merchantEntity2.setName("Name");
        merchantEntity2.setPassword("iloveyou");
        merchantEntity2.setPayme(true);
        merchantEntity2.setSecretKey("EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY");
        merchantEntity2.setUcell(true);
        merchantEntity2.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        merchantEntity2.setUpdatedDate(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        merchantEntity2.setUsername("janedoe");
        merchantEntity2.setYandex(true);

        MerchantEntity merchantEntity3 = new MerchantEntity();
        merchantEntity3.setActive(true);
        merchantEntity3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        merchantEntity3.setCreatedDate(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        merchantEntity3.setId(123L);
        merchantEntity3.setName("Name");
        merchantEntity3.setPassword("iloveyou");
        merchantEntity3.setPayme(true);
        merchantEntity3.setSecretKey("EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY");
        merchantEntity3.setUcell(true);
        merchantEntity3.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        merchantEntity3.setUpdatedDate(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        merchantEntity3.setUsername("janedoe");
        merchantEntity3.setYandex(true);

        MerchantServiceEntity merchantServiceEntity1 = new MerchantServiceEntity();
        merchantServiceEntity1.setActive(true);
        merchantServiceEntity1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult18 = LocalDate.of(1970, 1, 1).atStartOfDay();
        merchantServiceEntity1.setCreatedDate(Date.from(atStartOfDayResult18.atZone(ZoneId.of("UTC")).toInstant()));
        merchantServiceEntity1.setId(123L);
        merchantServiceEntity1.setMerchantEntity(merchantEntity3);
        merchantServiceEntity1.setMerchantServiceId(123L);
        merchantServiceEntity1.setName("Name");
        merchantServiceEntity1.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult19 = LocalDate.of(1970, 1, 1).atStartOfDay();
        merchantServiceEntity1.setUpdatedDate(Date.from(atStartOfDayResult19.atZone(ZoneId.of("UTC")).toInstant()));

        OsonServiceEntity osonServiceEntity1 = new OsonServiceEntity();
        osonServiceEntity1.setActive(true);
        osonServiceEntity1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult20 = LocalDate.of(1970, 1, 1).atStartOfDay();
        osonServiceEntity1.setCreatedDate(Date.from(atStartOfDayResult20.atZone(ZoneId.of("UTC")).toInstant()));
        osonServiceEntity1.setId(123L);
        osonServiceEntity1.setMerchantServiceEntity(merchantServiceEntity1);
        osonServiceEntity1.setName("Name");
        osonServiceEntity1.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult21 = LocalDate.of(1970, 1, 1).atStartOfDay();
        osonServiceEntity1.setUpdatedDate(Date.from(atStartOfDayResult21.atZone(ZoneId.of("UTC")).toInstant()));

        TransactionEntity transactionEntity1 = new TransactionEntity();
        transactionEntity1.setActive(true);
        transactionEntity1.setAgent(agentEntity1);
        transactionEntity1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult22 = LocalDate.of(1970, 1, 1).atStartOfDay();
        transactionEntity1.setCreatedDate(Date.from(atStartOfDayResult22.atZone(ZoneId.of("UTC")).toInstant()));
        transactionEntity1.setId(123L);
        transactionEntity1.setMerchant(merchantEntity2);
        transactionEntity1.setOsonServiceEntity(osonServiceEntity1);
        transactionEntity1.setTransactionAccount("3");
        transactionEntity1.setTransactionAmountFromAgent(BigDecimal.valueOf(42L));
        transactionEntity1.setTransactionAmountToMerchant(BigDecimal.valueOf(42L));
        transactionEntity1.setTransactionState(1);
        transactionEntity1.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult23 = LocalDate.of(1970, 1, 1).atStartOfDay();
        transactionEntity1.setUpdatedDate(Date.from(atStartOfDayResult23.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.transactionRepository.save((TransactionEntity) any())).thenReturn(transactionEntity1);
        when(this.transactionRepository.findById((Long) any())).thenReturn(ofResult);

        AgentServiceEntity agentServiceEntity = new AgentServiceEntity();
        agentServiceEntity.setActive(true);
        agentServiceEntity.setAgentId(123L);
        agentServiceEntity.setCommission(10.0d);
        agentServiceEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult24 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agentServiceEntity.setCreatedDate(Date.from(atStartOfDayResult24.atZone(ZoneId.of("UTC")).toInstant()));
        agentServiceEntity.setId(123L);
        agentServiceEntity.setServiceId(123L);
        agentServiceEntity.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult25 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agentServiceEntity.setUpdatedDate(Date.from(atStartOfDayResult25.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<AgentServiceEntity> ofResult1 = Optional.of(agentServiceEntity);
        when(this.agentServiceRepository.findByServiceIdAndAgentId((Long) any(), (Long) any())).thenReturn(ofResult1);

        AgentEntity agentEntity2 = new AgentEntity();
        agentEntity2.setActive(true);
        agentEntity2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult26 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agentEntity2.setCreatedDate(Date.from(atStartOfDayResult26.atZone(ZoneId.of("UTC")).toInstant()));
        agentEntity2.setId(123L);
        agentEntity2.setIsApelsin(true);
        agentEntity2.setIsClick(true);
        agentEntity2.setIsPayme(true);
        agentEntity2.setIsPaynet(true);
        agentEntity2.setName("Name");
        agentEntity2.setPassword("iloveyou");
        agentEntity2.setPermission("Permission");
        agentEntity2.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult27 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agentEntity2.setUpdatedDate(Date.from(atStartOfDayResult27.atZone(ZoneId.of("UTC")).toInstant()));
        agentEntity2.setUsername("janedoe");

        AgentDepositEntity agentDepositEntity = new AgentDepositEntity();
        agentDepositEntity.setActive(true);
        agentDepositEntity.setAgentEntity(agentEntity2);
        agentDepositEntity.setAmount(BigDecimal.valueOf(42L));
        agentDepositEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult28 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agentDepositEntity.setCreatedDate(Date.from(atStartOfDayResult28.atZone(ZoneId.of("UTC")).toInstant()));
        agentDepositEntity.setId(123L);
        agentDepositEntity.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult29 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agentDepositEntity.setUpdatedDate(Date.from(atStartOfDayResult29.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<AgentDepositEntity> ofResult2 = Optional.of(agentDepositEntity);
        when(this.agentRepository.getAgentDepositByUsername((String) any())).thenReturn(ofResult2);
        assertNull(this.paymentService.pay(123L, "janedoe"));
        verify(this.transactionRepository).save((TransactionEntity) any());
        verify(this.transactionRepository).findById((Long) any());
        verify(this.agentServiceRepository).findByServiceIdAndAgentId((Long) any(), (Long) any());
        verify(this.agentRepository).getAgentDepositByUsername((String) any());
    }

    /**
     * Method under test: {@link PaymentService#pay(Long, String)}
     */
    @Test
    void testPay2() {
        AgentEntity agentEntity = new AgentEntity();
        agentEntity.setActive(true);
        agentEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        agentEntity.setCreatedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        agentEntity.setId(123L);
        agentEntity.setIsApelsin(true);
        agentEntity.setIsClick(true);
        agentEntity.setIsPayme(true);
        agentEntity.setIsPaynet(true);
        agentEntity.setName("Name");
        agentEntity.setPassword("iloveyou");
        agentEntity.setPermission("Permission");
        agentEntity.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agentEntity.setUpdatedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        agentEntity.setUsername("janedoe");

        MerchantEntity merchantEntity = new MerchantEntity();
        merchantEntity.setActive(true);
        merchantEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        merchantEntity.setCreatedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        merchantEntity.setId(123L);
        merchantEntity.setName("Name");
        merchantEntity.setPassword("iloveyou");
        merchantEntity.setPayme(true);
        merchantEntity.setSecretKey("EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY");
        merchantEntity.setUcell(true);
        merchantEntity.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        merchantEntity.setUpdatedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        merchantEntity.setUsername("janedoe");
        merchantEntity.setYandex(true);

        MerchantEntity merchantEntity1 = new MerchantEntity();
        merchantEntity1.setActive(true);
        merchantEntity1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        merchantEntity1.setCreatedDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        merchantEntity1.setId(123L);
        merchantEntity1.setName("Name");
        merchantEntity1.setPassword("iloveyou");
        merchantEntity1.setPayme(true);
        merchantEntity1.setSecretKey("EXAMPLEKEYwjalrXUtnFEMI/K7MDENG/bPxRfiCY");
        merchantEntity1.setUcell(true);
        merchantEntity1.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        merchantEntity1.setUpdatedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        merchantEntity1.setUsername("janedoe");
        merchantEntity1.setYandex(true);

        MerchantServiceEntity merchantServiceEntity = new MerchantServiceEntity();
        merchantServiceEntity.setActive(true);
        merchantServiceEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        merchantServiceEntity.setCreatedDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        merchantServiceEntity.setId(123L);
        merchantServiceEntity.setMerchantEntity(merchantEntity1);
        merchantServiceEntity.setMerchantServiceId(123L);
        merchantServiceEntity.setName("Name");
        merchantServiceEntity.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        merchantServiceEntity.setUpdatedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));

        OsonServiceEntity osonServiceEntity = new OsonServiceEntity();
        osonServiceEntity.setActive(true);
        osonServiceEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        osonServiceEntity.setCreatedDate(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        osonServiceEntity.setId(123L);
        osonServiceEntity.setMerchantServiceEntity(merchantServiceEntity);
        osonServiceEntity.setName("Name");
        osonServiceEntity.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        osonServiceEntity.setUpdatedDate(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));

        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setActive(true);
        transactionEntity.setAgent(agentEntity);
        transactionEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        transactionEntity.setCreatedDate(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        transactionEntity.setId(123L);
        transactionEntity.setMerchant(merchantEntity);
        transactionEntity.setOsonServiceEntity(osonServiceEntity);
        transactionEntity.setTransactionAccount("3");
        transactionEntity.setTransactionAmountFromAgent(BigDecimal.valueOf(42L));
        transactionEntity.setTransactionAmountToMerchant(BigDecimal.valueOf(42L));
        transactionEntity.setTransactionState(1);
        transactionEntity.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult11 = LocalDate.of(1970, 1, 1).atStartOfDay();
        transactionEntity.setUpdatedDate(Date.from(atStartOfDayResult11.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<TransactionEntity> ofResult = Optional.of(transactionEntity);
        when(this.transactionRepository.save((TransactionEntity) any()))
                .thenThrow(new DataNotFoundException("An error occurred"));
        when(this.transactionRepository.findById((Long) any())).thenReturn(ofResult);

        AgentServiceEntity agentServiceEntity = new AgentServiceEntity();
        agentServiceEntity.setActive(true);
        agentServiceEntity.setAgentId(123L);
        agentServiceEntity.setCommission(10.0d);
        agentServiceEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult12 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agentServiceEntity.setCreatedDate(Date.from(atStartOfDayResult12.atZone(ZoneId.of("UTC")).toInstant()));
        agentServiceEntity.setId(123L);
        agentServiceEntity.setServiceId(123L);
        agentServiceEntity.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult13 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agentServiceEntity.setUpdatedDate(Date.from(atStartOfDayResult13.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<AgentServiceEntity> ofResult1 = Optional.of(agentServiceEntity);
        when(this.agentServiceRepository.findByServiceIdAndAgentId((Long) any(), (Long) any())).thenReturn(ofResult1);

        AgentEntity agentEntity1 = new AgentEntity();
        agentEntity1.setActive(true);
        agentEntity1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult14 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agentEntity1.setCreatedDate(Date.from(atStartOfDayResult14.atZone(ZoneId.of("UTC")).toInstant()));
        agentEntity1.setId(123L);
        agentEntity1.setIsApelsin(true);
        agentEntity1.setIsClick(true);
        agentEntity1.setIsPayme(true);
        agentEntity1.setIsPaynet(true);
        agentEntity1.setName("Name");
        agentEntity1.setPassword("iloveyou");
        agentEntity1.setPermission("Permission");
        agentEntity1.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult15 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agentEntity1.setUpdatedDate(Date.from(atStartOfDayResult15.atZone(ZoneId.of("UTC")).toInstant()));
        agentEntity1.setUsername("janedoe");

        AgentDepositEntity agentDepositEntity = new AgentDepositEntity();
        agentDepositEntity.setActive(true);
        agentDepositEntity.setAgentEntity(agentEntity1);
        agentDepositEntity.setAmount(BigDecimal.valueOf(42L));
        agentDepositEntity.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        LocalDateTime atStartOfDayResult16 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agentDepositEntity.setCreatedDate(Date.from(atStartOfDayResult16.atZone(ZoneId.of("UTC")).toInstant()));
        agentDepositEntity.setId(123L);
        agentDepositEntity.setUpdatedBy("2020-03-01");
        LocalDateTime atStartOfDayResult17 = LocalDate.of(1970, 1, 1).atStartOfDay();
        agentDepositEntity.setUpdatedDate(Date.from(atStartOfDayResult17.atZone(ZoneId.of("UTC")).toInstant()));
        Optional<AgentDepositEntity> ofResult2 = Optional.of(agentDepositEntity);
        when(this.agentRepository.getAgentDepositByUsername((String) any())).thenReturn(ofResult2);
        assertThrows(DataNotFoundException.class, () -> this.paymentService.pay(123L, "janedoe"));
        verify(this.transactionRepository).save((TransactionEntity) any());
        verify(this.transactionRepository).findById((Long) any());
        verify(this.agentServiceRepository).findByServiceIdAndAgentId((Long) any(), (Long) any());
        verify(this.agentRepository).getAgentDepositByUsername((String) any());
    }
}

