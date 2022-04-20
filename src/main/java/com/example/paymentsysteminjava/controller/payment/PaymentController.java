package com.example.paymentsysteminjava.controller.payment;

import com.example.paymentsysteminjava.dto.response.agent.BaseAgentResponse;
import com.example.paymentsysteminjava.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PreAuthorize("hasRole('ROLE_AGENT')")
    @PostMapping(value = "/pay",
            consumes
                    = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE},
            produces =
                    {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    public BaseAgentResponse pay(
            @RequestParam("id") Long transactionId,
            @AuthenticationPrincipal String username
    ) {
        return paymentService.pay(transactionId, username);
    }

}
