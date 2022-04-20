package com.example.paymentsysteminjava.service.user;

import com.example.paymentsysteminjava.dto.payload.user.UserLoginDto;
import com.example.paymentsysteminjava.dto.payload.user.UserRegisterDto;
import com.example.paymentsysteminjava.entity.user.UserEntity;
import com.example.paymentsysteminjava.repository.UserRepository;
import com.example.paymentsysteminjava.service.BaseService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserService extends BaseService {

    default UserEntity login(UserLoginDto userLoginDto, UserRepository userRepository, PasswordEncoder passwordEncoder){
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(userLoginDto.getUsername());
        if (optionalUserEntity.isEmpty())
            return null;

        if (!passwordEncoder.matches(userLoginDto.getPassword(),optionalUserEntity.get().getPassword()))
            return null;

        return optionalUserEntity.get();
    }
    Boolean add(UserRegisterDto userRegisterDto);
    Optional<UserEntity> getUser(Long id);
}
