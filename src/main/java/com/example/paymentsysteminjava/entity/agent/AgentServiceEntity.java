package com.example.paymentsysteminjava.entity.agent;

import com.example.paymentsysteminjava.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class AgentServiceEntity extends BaseEntity {

    private Long agentId;
    private Long serviceId;
    private double commission;

}
