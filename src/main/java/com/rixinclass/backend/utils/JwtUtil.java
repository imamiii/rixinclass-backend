package com.rixinclass.backend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    // JWT密钥（建议在配置文件中配置，这里默认值避免报错）
    @Value("${jwt.secret:defaultSecret123456}")
    private String secret;

    // 令牌有效期（秒），默认24小时
    @Value("${jwt.expire:86400}")
    private long expireSeconds;

    /**
     * 生成令牌
     * @param userId 用户ID
     * @param role 角色（teacher/admin）
     * @return 令牌字符串
     */
    public String generateToken(Long userId, String role) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expireSeconds * 1000);

        return Jwts.builder()
                .setSubject(userId.toString()) // 存储用户ID
                .claim("role", role) // 存储角色信息
                .setIssuedAt(now) // 签发时间
                .setExpiration(expireDate) // 过期时间
                .signWith(SignatureAlgorithm.HS256, secret) // 签名算法+密钥
                .compact();
    }

    /**
     * 从令牌中获取用户ID（原方法，保持内部逻辑不变）
     * @param token 令牌字符串
     * @return 用户ID（无效令牌返回null）
     */
    public Long getUserId(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return Long.parseLong(claims.getSubject());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 补充：与调用处方法名匹配的方法（解决报错）
     * 直接调用已有的getUserId方法，保持功能一致
     */
    public Long getUserIdFromToken(String token) {
        return getUserId(token); // 复用现有逻辑，避免代码冗余
    }

    /**
     * 从令牌中获取角色
     * @param token 令牌字符串
     * @return 角色（teacher/admin，无效令牌返回null）
     */
    public String getRole(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("role", String.class);
        } catch (Exception e) {
            return null;
        }
    }
}