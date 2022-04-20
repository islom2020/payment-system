package com.example.paymentsysteminjava.repository.merchant;

import com.example.paymentsysteminjava.entity.merchant.MerchantEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<MerchantEntity, Long> {

    @Query("select m from MerchantEntity m where m.id = ?1")
    Optional<MerchantServiceEntity> getMerchantServiceEntityById(Long id);
}
