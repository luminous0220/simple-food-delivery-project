package io.github.luminous0220.foodDelivery.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.spec.SecretKeySpec;

import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    /**
     * 生成jwt
     * 使用Hs256算法, 私匙使用固定秘钥
     *
     * @param secretKey jwt秘钥
     * @param ttlMillis jwt过期时间(毫秒)
     * @param claims    设置的信息
     * @return
     */
    public static String createJwt(String secretKey, long ttlMillis, Map<String,Object> claims) {

        // 指定签名算法为HS256（HMAC-SHA256）
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;


        // 计算JWT过期时间：当前时间 + 传入的存活时间（毫秒）
        long expMillis = System.currentTimeMillis() + ttlMillis;
        Date exp = new Date(expMillis);

        // 将Base64编码的密钥字符串解码为字节数组
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);

        // 使用密钥字节数组和算法名称生成签名密钥对象
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // 创建JWT构建器，并设置以下内容：
        // 1. 自定义的负载信息（claims）
        // 2. 使用指定的签名密钥和算法进行签名
        // 3. 设置过期时间
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims) // 添加自定义声明（用户信息等）
                .signWith(signingKey, signatureAlgorithm) // 签名算法及密钥
                .setExpiration(exp); // 设置过期时间

        // 生成并返回紧凑的JWT字符串（Header.Payload.Signature格式）
        return builder.compact();
    }

    /**
     * 解析jwt
     *
     * @param secretKey jwt秘钥
     * @param token     jwt字符串
     * @return
     */

    public static Claims parseJWT(String secretKey, String token) {

        // 将Base64编码的密钥字符串解码为字节数组
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);

        // 创建JWT解析器并配置签名密钥
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(apiKeySecretBytes)  // 设置字节数组密钥，用于解析
                .build()
                .parseClaimsJws(token)  // 解析并验证JWT签名，失败则抛出异常（如SignatureException）
                .getBody();

        return claims;
    }
}