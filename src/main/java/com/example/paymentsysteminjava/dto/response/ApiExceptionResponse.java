package com.example.paymentsysteminjava.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiExceptionResponse {
        private int status;
        private String message;
}
