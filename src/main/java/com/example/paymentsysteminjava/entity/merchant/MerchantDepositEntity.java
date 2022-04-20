package com.example.paymentsysteminjava.entity.merchant;

import com.example.paymentsysteminjava.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class MerchantDepositEntity extends BaseEntity {
    @OneToOne
    private MerchantEntity merchantEntity;

    public MerchantDepositEntity(MerchantEntity merchantEntity) {
        this.merchantEntity = merchantEntity;
    }

    private BigDecimal amount = BigDecimal.valueOf(10000);
}
