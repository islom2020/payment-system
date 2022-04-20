package com.example.paymentsysteminjava.controller.agent;

import com.example.paymentsysteminjava.dto.payload.user.UserRegisterDto;
import com.example.paymentsysteminjava.dto.response.BaseApiResponse;
import com.example.paymentsysteminjava.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/agent")
public class AgentController {

    @Autowired
    @Qualifier("a")
    private  UserService userService;

    @PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
    @PostMapping("/register")
    public ResponseEntity<?> add(@RequestBody UserRegisterDto userRegisterDto) {


        Boolean add = userService.add(userRegisterDto);

        if(add)
            return ResponseEntity.ok().body(new BaseApiResponse(1, "created", true ));

        return ResponseEntity.status(HttpStatus.CONFLICT).body(new BaseApiResponse(1, "creation error", false ));
    }

}