package com.example.paymentsysteminjava.controller.merchant;

import com.example.paymentsysteminjava.dto.payload.merchant.MerchantServiceRegisterDto;
import com.example.paymentsysteminjava.dto.response.BaseApiResponse;
import com.example.paymentsysteminjava.service.merchant.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchant/service")
public class MerchantServiceController {

    @Autowired
    @Qualifier("merchantServiceServiceImp")
    private  MerchantService<MerchantServiceRegisterDto> merchantService;

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody MerchantServiceRegisterDto merchantServiceRegisterDto) {

        Boolean add = merchantService.add(merchantServiceRegisterDto);

        if (add)
            return ResponseEntity.ok().body(new BaseApiResponse(1, "Created", true));

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new BaseApiResponse(0, "creation error", false));
    }
}
