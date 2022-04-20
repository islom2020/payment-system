package com.example.paymentsysteminjava.service.merchant;

import com.example.paymentsysteminjava.dto.payload.merchant.MerchantServiceRegisterDto;
import com.example.paymentsysteminjava.entity.merchant.MerchantEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantServiceEntity;
import com.example.paymentsysteminjava.repository.merchant.MerchantRepository;
import com.example.paymentsysteminjava.repository.merchant.MerchantServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("merchantServiceServiceImp")
@RequiredArgsConstructor
public class MerchantServiceServiceImp implements MerchantService<MerchantServiceRegisterDto> {

    private final MerchantServiceRepository merchantServiceRepository;
    private final MerchantRepository merchantRepository;

    @Override
    public Boolean add(MerchantServiceRegisterDto merchantServiceRegisterDto){
        Optional<MerchantEntity> optionalMerchant = merchantRepository.findById(merchantServiceRegisterDto.getMerchantId());

        //// TODO: 11.04.2022 add another appropriate exception
        if(optionalMerchant.isEmpty())
            throw new UsernameNotFoundException("merchant not found");

        MerchantServiceEntity merchantServiceEntity = new MerchantServiceEntity();
        merchantServiceEntity.setMerchantEntity(optionalMerchant.get());
        merchantServiceEntity.setMerchantServiceId(merchantServiceRegisterDto.getMerchantServiceId());
        merchantServiceEntity.setName(merchantServiceRegisterDto.getName());
        merchantServiceRepository.save(merchantServiceEntity);

        return true;
    }

}
