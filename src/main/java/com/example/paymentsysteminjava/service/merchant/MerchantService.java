package com.example.paymentsysteminjava.service.merchant;

import com.example.paymentsysteminjava.service.BaseService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface MerchantService<T> extends BaseService {
    Boolean add(T t);
}
