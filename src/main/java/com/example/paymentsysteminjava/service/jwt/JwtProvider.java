package com.example.paymentsysteminjava.service.jwt;

import com.example.paymentsysteminjava.entity.user.UserEntity;
import com.example.paymentsysteminjava.exception.JwtExpiredTokenException;
import com.example.paymentsysteminjava.exception.JwtValidationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.access.token.secret.key}")
    public String accessTokenSecretKey;

    @Value("${jwt.refresh.token.secret.key}")
    public String refreshTokenSecretKey;

    @Value("${jwt.access.token.expired.time}")
    public long accessTokenExpiredTime;

    @Value("${jwt.refresh.token.expired.time}")
    public long refreshTokenExpiredTime;

    public String generateAccessToken(UserEntity userEntity) {
        return "Bearer " + Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, accessTokenSecretKey)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + accessTokenExpiredTime))
                .setSubject(userEntity.getUsername())
                .claim("authorities", userEntity.getPermission())
                .compact();
    }

    public String generateRefreshToken(UserEntity userEntity) {
        return "Bearer " + Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, refreshTokenSecretKey)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + refreshTokenExpiredTime))
                .setSubject(userEntity.getUsername())
                .claim("authorities", userEntity.getPermission())
                .compact();
    }

    public Claims parseAccessToken(String accessToken) throws JwtExpiredTokenException {
        try {
            return Jwts.parser().setSigningKey(accessTokenSecretKey)
                    .parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException ex) {
            throw new JwtExpiredTokenException("ACCESS TOKEN EXPIRED");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new JwtValidationException("TOKEN VALIDATION ERROR");
        }
    }

    public String getAccessTokenFromRefreshToken(String refreshToken) throws JwtExpiredTokenException {
        Claims body;
        try {
            body = Jwts.parser()
                    .setSigningKey(refreshTokenSecretKey)
                    .parseClaimsJws(refreshToken)
                    .getBody();
        } catch (ExpiredJwtException ex) {
            throw new JwtExpiredTokenException("REFRESH TOKEN EXPIRED");
        } catch (Exception ex) {
            return null;
        }

        return generateAccessToken(body.get("user", UserEntity.class));
    }


}
