package com.example.paymentsysteminjava.repository.agent;

import com.example.paymentsysteminjava.entity.agent.AgentDepositEntity;
import com.example.paymentsysteminjava.entity.agent.AgentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<AgentEntity, Long> {

    @Query("select a from AgentEntity a where a.username = ?1 and a.isActive = true ")
    AgentEntity findByUsername(String username);

    @Query("select a from AgentDepositEntity a where a.agentEntity.username = ?1")
    Optional<AgentDepositEntity> getAgentDepositByUsername(String username);


}
