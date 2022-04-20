package com.example.paymentsysteminjava.service.oson;

import com.example.paymentsysteminjava.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public interface OsonService extends BaseService {
    Boolean add(Long merchantServiceId);
}
