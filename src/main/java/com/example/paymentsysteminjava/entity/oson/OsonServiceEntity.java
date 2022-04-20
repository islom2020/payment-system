package com.example.paymentsysteminjava.entity.oson;

import com.example.paymentsysteminjava.entity.BaseEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class OsonServiceEntity extends BaseEntity {

    private String name;

    @OneToOne
    private MerchantServiceEntity merchantServiceEntity;
}
