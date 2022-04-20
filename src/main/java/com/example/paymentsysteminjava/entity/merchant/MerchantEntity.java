package com.example.paymentsysteminjava.entity.merchant;

import com.example.paymentsysteminjava.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
public class MerchantEntity extends BaseEntity {
    private String name;

    @Column(unique = true)
    private String username;
    private String password;

    private String secretKey;


    @Transient
    private boolean isUcell;
    @Transient
    private boolean isYandex;
    @Transient
    private boolean isPayme;

    public boolean isPaynet(){
        return (super.id == 10);
    }

    public boolean isPayme(){
        return (super.id == 4);
    }

    public boolean isClick(){
        return (super.id == 9);
    }
}
