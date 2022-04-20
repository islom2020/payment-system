package com.example.paymentsysteminjava.service.agent;

import com.example.paymentsysteminjava.dto.payload.user.UserRegisterDto;
import com.example.paymentsysteminjava.entity.agent.AgentDepositEntity;
import com.example.paymentsysteminjava.entity.agent.AgentEntity;
import com.example.paymentsysteminjava.entity.user.UserEntity;
import com.example.paymentsysteminjava.exception.LoginValidationException;
import com.example.paymentsysteminjava.repository.agent.AgentDepositRepository;
import com.example.paymentsysteminjava.repository.agent.AgentRepository;
import com.example.paymentsysteminjava.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service("a")
@RequiredArgsConstructor
public class AgentServiceImp implements UserService {

    private final AgentRepository agentRepository;
    private final AgentDepositRepository agentDepositRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean add(UserRegisterDto userRegisterDto) {
        AgentEntity agent = agentRepository.findByUsername(userRegisterDto.getUsername());
        if (agent != null)
            throw new LoginValidationException("username is already exists");

        AgentEntity agentEntity = new AgentEntity();
        agentEntity.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        agentEntity.setPermission(userRegisterDto.getPermission().get(0));
        agentEntity.setName(userRegisterDto.getName());
        agentEntity.setUsername(userRegisterDto.getUsername());


        AgentEntity savedEntity = agentRepository.save(agentEntity);
        agentDepositRepository.save(new AgentDepositEntity(savedEntity, BigDecimal.valueOf(10000)));

        return true;
    }

    @Override
    public Optional<UserEntity> getUser(Long id) {
        return Optional.empty();
    }


}
