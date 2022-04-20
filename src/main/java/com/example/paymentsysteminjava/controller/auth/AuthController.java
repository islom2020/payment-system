package com.example.paymentsysteminjava.controller.auth;

import com.example.paymentsysteminjava.dto.payload.user.UserLoginDto;
import com.example.paymentsysteminjava.dto.payload.user.UserRegisterDto;
import com.example.paymentsysteminjava.dto.response.ApiJwtResponse;
import com.example.paymentsysteminjava.dto.response.BaseApiResponse;
import com.example.paymentsysteminjava.entity.user.UserEntity;
import com.example.paymentsysteminjava.exception.LoginValidationException;
import com.example.paymentsysteminjava.repository.UserRepository;
import com.example.paymentsysteminjava.service.jwt.JwtProvider;
import com.example.paymentsysteminjava.service.user.UserService;
import com.example.paymentsysteminjava.service.user.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final JwtProvider jwtProvider;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserServiceImp userServiceImp;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/agent/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {
        UserEntity agentEntity =  userService.login(userLoginDto, userRepository, passwordEncoder);
        if(agentEntity == null)
            return ResponseEntity.ok().body(new BaseApiResponse(0, "username or password is incorrect", null));


        String accessToken = jwtProvider.generateAccessToken(agentEntity);
        String refreshToken = jwtProvider.generateRefreshToken(agentEntity);

        return ResponseEntity.ok().body(new ApiJwtResponse(1, accessToken, refreshToken));
    }

    @PostMapping(value = "/user/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getToken(@RequestBody UserLoginDto userLoginDto) {
        UserEntity userEntity =  userService.login(userLoginDto,userRepository,passwordEncoder);
        if(userEntity == null)
            return ResponseEntity.ok().body(new BaseApiResponse(0, "username or password is incorrect", null));


        String accessToken = jwtProvider.generateAccessToken(userEntity);
        String refreshToken = jwtProvider.generateRefreshToken(userEntity);

        return ResponseEntity.ok().body(new ApiJwtResponse(1, accessToken, refreshToken));
    }

//    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @PostMapping("/refresh_token")
    public ResponseEntity<?> getRefreshToken(@RequestBody String token) {
        String accessToken = jwtProvider.getAccessTokenFromRefreshToken(token);
        return ResponseEntity.ok().body(new ApiJwtResponse(1, accessToken, token));
    }

    @PostMapping("/add/admin")
    public ResponseEntity<Boolean> addSuperAdmin(@RequestBody UserRegisterDto userRegisterDto){
        return ResponseEntity.ok(userServiceImp.addSuperAdmin(userRegisterDto));
    }


}
