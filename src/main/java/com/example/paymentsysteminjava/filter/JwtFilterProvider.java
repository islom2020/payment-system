package com.example.paymentsysteminjava.filter;

import com.example.paymentsysteminjava.dto.response.ApiExceptionResponse;
import com.example.paymentsysteminjava.entity.user.UserEntity;
import com.example.paymentsysteminjava.exception.JwtExpiredTokenException;
import com.example.paymentsysteminjava.exception.JwtValidationException;
import com.example.paymentsysteminjava.service.jwt.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilterProvider extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal
            (HttpServletRequest request,
             HttpServletResponse response,
             FilterChain filterChain) throws ServletException, IOException {

        String tokenFromRequest = getTokenFromRequest(request);
        if (tokenFromRequest == null) {
            filterChain.doFilter(request, response);
            return;
        }

        Claims claims = null;
        try{
            claims = jwtProvider.parseAccessToken(tokenFromRequest);
        } catch (JwtExpiredTokenException | JwtValidationException e){
            setErrorResponse(response, e.getMessage());
            return;
        }


        UserEntity userEntity = new UserEntity();
        userEntity.setPermission((String) claims.get("authorities"));

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                claims.getSubject(),
                null,
                userEntity.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer "))
            return authorization.substring(7);

        return null;
    }

    private void setErrorResponse(HttpServletResponse response, String errorMessage){
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        try {
            response.getWriter()
                    .write(objectMapper.writeValueAsString(new ApiExceptionResponse(100, errorMessage)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
