package com.example.paymentsysteminjava.entity.agent;

import com.example.paymentsysteminjava.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AgentDepositEntity extends BaseEntity {

    @OneToOne
    AgentEntity agentEntity;
    BigDecimal amount = BigDecimal.valueOf(1000);
}
