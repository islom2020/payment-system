package com.example.paymentsysteminjava.repository.merchant;

import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface MerchantServiceRepository extends JpaRepository<MerchantServiceEntity, Long> {
    @Query("select m from MerchantServiceEntity m where m.merchantServiceId = ?1")
    Optional<MerchantServiceEntity> findById(Long id);

    MerchantServiceEntity findByMerchantServiceId(Long id);
}
