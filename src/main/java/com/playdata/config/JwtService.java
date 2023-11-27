package com.playdata.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JwtService {

    @Value("${config.jwt.secret}")
    private String secretKey;


    public TokenInfo parseToken(String token){
        Claims body = (Claims) Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parse(token)
                .getBody();

        return TokenInfo.builder()
                .id(UUID.fromString(body.get("id", String.class)))
                .email(body.get("email", String.class))
                .nickname(body.get("nickname", String.class))
                .profileImageUrl(body.get("profileImageUrl", String.class))
                .build();
    }
}
