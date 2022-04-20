package com.example.paymentsysteminjava.entity.merchant;

import com.example.paymentsysteminjava.entity.BaseEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class MerchantServiceEntity extends BaseEntity {

    private String name;

    private Long merchantServiceId;

    @ManyToOne
    private MerchantEntity merchantEntity;
}
