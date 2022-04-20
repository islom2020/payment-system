package com.example.paymentsysteminjava.service.oson;

import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import com.example.paymentsysteminjava.entity.oson.OsonServiceEntity;
import com.example.paymentsysteminjava.repository.OsonRepository;
import com.example.paymentsysteminjava.repository.merchant.MerchantServiceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OsonServiceImp implements OsonService{

    private final OsonRepository osonRepository;
    private final MerchantServiceRepository merchantServiceRepository;
    private final ModelMapper modelMapper;

    @Override
    public Boolean add(Long merchantServiceId) {

        MerchantServiceEntity merchantServiceEntity
                = merchantServiceRepository.findByMerchantServiceId(merchantServiceId);

        //TODO: 11.04.2022 use appropriate exception
        if(merchantServiceEntity == null)
            throw new UsernameNotFoundException("merchant service not found");

        OsonServiceEntity osonServiceEntity = new OsonServiceEntity();
        osonServiceEntity.setMerchantServiceEntity(merchantServiceEntity);
        osonRepository.save(osonServiceEntity);
        return true;
    }
}
