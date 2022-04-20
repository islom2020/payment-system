package com.example.paymentsysteminjava.service.merchant;

import com.example.paymentsysteminjava.dto.payload.merchant.MerchantRegisterDto;
import com.example.paymentsysteminjava.entity.merchant.MerchantDepositEntity;
import com.example.paymentsysteminjava.entity.merchant.MerchantEntity;
import com.example.paymentsysteminjava.repository.merchant.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Primary
@Service("merchantServiceImp")
@RequiredArgsConstructor
public class MerchantServiceImp implements MerchantService<MerchantRegisterDto>{

    private final MerchantRepository merchantRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean add(MerchantRegisterDto merchantRegisterDto){
        MerchantEntity merchantEntity = modelMapper.map(merchantRegisterDto, MerchantEntity.class);
        merchantEntity.setPassword(passwordEncoder.encode(merchantEntity.getPassword()));
        new MerchantDepositEntity(merchantRepository.save(merchantEntity));
        //// TODO: 12.04.2022  add merchantDeposit
        return true;
    }
}
