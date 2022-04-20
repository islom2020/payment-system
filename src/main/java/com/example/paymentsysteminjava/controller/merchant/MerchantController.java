package com.example.paymentsysteminjava.controller.merchant;

import com.example.paymentsysteminjava.dto.payload.merchant.MerchantRegisterDto;
import com.example.paymentsysteminjava.dto.response.BaseApiResponse;
import com.example.paymentsysteminjava.service.merchant.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchant")
@RequiredArgsConstructor
public class MerchantController {

    @Qualifier("merchantServiceImp")
    private final MerchantService<MerchantRegisterDto> merchantService;

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody MerchantRegisterDto merchantRegisterDto) {


        Boolean add = merchantService.add(merchantRegisterDto);

        if (add)
            return ResponseEntity.ok().body(new BaseApiResponse(1, "Created", true));

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new BaseApiResponse(0, "creation error", false));
    }


}
