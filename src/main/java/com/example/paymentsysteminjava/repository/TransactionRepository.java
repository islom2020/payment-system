package com.example.paymentsysteminjava.repository;

import com.example.paymentsysteminjava.entity.transaction.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
