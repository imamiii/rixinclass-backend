package com.rixinclass.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private final Key key;
    private final long expirationMs;

    public JwtUtil(@Value("${jwt.secret}") String secretBase64,
                   @Value("${jwt.expiration}") long expirationMs) {
        byte[] keyBytes = java.util.Base64.getDecoder().decode(secretBase64);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.expirationMs = expirationMs;
    }

    // 通用生成令牌方法
    public String generate(String subject, Map<String, Object> claims) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expirationMs);
        return Jwts.builder()
                .setSubject(subject)
                .addClaims(claims)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 生成用户令牌（原utils中的方法）
    public String generateToken(Long userId, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        return generate(userId.toString(), claims);
    }

    // 解析令牌
    public Jws<Claims> parse(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }

    // 从令牌中获取用户ID
    public Long getUserId(String token) {
        try {
            Claims claims = parse(token).getBody();
            return Long.parseLong(claims.getSubject());
        } catch (Exception e) {
            return null;
        }
    }

    public Long getUserIdFromToken(String token) {
        return getUserId(token);
    }

    // 从令牌中获取角色
    public String getRole(String token) {
        try {
            Claims claims = parse(token).getBody();
            return claims.get("role", String.class);
        } catch (Exception e) {
            return null;
        }
    }
}