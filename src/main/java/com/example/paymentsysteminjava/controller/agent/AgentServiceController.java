package com.example.paymentsysteminjava.controller.agent;

import com.example.paymentsysteminjava.dto.payload.agent.AgentServiceDto;
import com.example.paymentsysteminjava.service.agent.AgentServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agent/service/")
@RequiredArgsConstructor
public class AgentServiceController {
    private final AgentServiceService agentServiceService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    @RequestMapping("/add")
    public ResponseEntity<?> add(@RequestBody AgentServiceDto agentServiceDto){
        return ResponseEntity.ok(agentServiceService.add(agentServiceDto));
    }
}
