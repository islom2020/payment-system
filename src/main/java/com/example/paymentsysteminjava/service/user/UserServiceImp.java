package com.example.paymentsysteminjava.service.user;

import com.example.paymentsysteminjava.dto.payload.user.UserRegisterDto;
import com.example.paymentsysteminjava.entity.user.UserEntity;
import com.example.paymentsysteminjava.exception.LoginValidationException;
import com.example.paymentsysteminjava.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Primary
@Service("u")
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean add(UserRegisterDto userRegisterDto) {

        Optional<UserEntity> optionalUser = userRepository.findByUsername(userRegisterDto.getUsername());
        if (optionalUser.isPresent())
            throw new LoginValidationException("username is already exists");

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        userEntity.setPermission("ROLE_USER");
        userEntity.setName(userRegisterDto.getName());
        userEntity.setUsername(userRegisterDto.getUsername());
        userRepository.save(userEntity);
        return true;
    }

    @Override
    public Optional<UserEntity> getUser(Long id) {
        return Optional.empty();
    }


    public Boolean  addSuperAdmin(UserRegisterDto userRegisterDto){

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        userEntity.setPermission(userRegisterDto.getPermission().get(0));
        userEntity.setName(userRegisterDto.getName());
        userEntity.setUsername(userRegisterDto.getUsername());

        userRepository.save(userEntity);
        return true;
    }

}
