package com.example.paymentsysteminjava.config;

import com.example.paymentsysteminjava.entity.user.UserEntity;
import com.example.paymentsysteminjava.service.auditing.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditingConfig {
    @Bean
    public AuditorAware<String> auditorProvider(){
        return new AuditorAwareImpl();
    }
}
