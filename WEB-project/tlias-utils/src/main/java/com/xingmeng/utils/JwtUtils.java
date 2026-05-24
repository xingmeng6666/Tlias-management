package com.xingmeng.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

/**
 * JWT令牌操作工具类
 * 提供令牌生成和解析功能
 */
public class JwtUtils {

    // 与测试类保持一致的签名密钥
    private static final String SIGNING_KEY = "eGluZ21lbmc=";
    
    // 令牌过期时间：12小时 (1000毫秒 * 3600秒 * 12小时)
    private static final long EXPIRATION_TIME = 1000L * 3600 * 12;

    /**
     * 生成JWT令牌
     * @param claims 要包含在令牌中的自定义声明数据
     * @return 生成的JWT令牌字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();
    }

    /**
     * 解析JWT令牌，获取令牌中的声明数据
     * @param token 要解析的JWT令牌字符串
     * @return 令牌中包含的Claims对象
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}