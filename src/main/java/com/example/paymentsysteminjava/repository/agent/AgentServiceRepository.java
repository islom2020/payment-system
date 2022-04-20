package com.example.paymentsysteminjava.repository.agent;

import com.example.paymentsysteminjava.entity.agent.AgentServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AgentServiceRepository extends JpaRepository<AgentServiceEntity, Long> {

    @Query("select a from AgentServiceEntity a where a.serviceId = ?1 and a.agentId = ?2")
    Optional<AgentServiceEntity> findByServiceIdAndAgentId(Long serviceId, Long agentId);
}
