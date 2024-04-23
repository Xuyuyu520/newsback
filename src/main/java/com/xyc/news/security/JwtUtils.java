/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xyc.news.security;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*** 
 */
public class JwtUtils {

    //SECRET 数字签名密钥
    static final String SECRET = "account";
    //EXPIRE 7天有效期
    static final long EXPIRE = 1000 * 60 * 60 * 24 * 7;
    //EXPECT 还有最后一天到期时刷新
    static final long EXPECT = 1000 * 60 * 60 * 24 * 1;

    /**
     * 解析token
     *
     * @param token 需要解析的
     * @return Claims
     */
    public static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            //过期等token无效
            claims = null;
        }
        return claims;
    }

    /**
     * 构建认证过的认证对象
     *
     * @param jws
     * @return
     */
    public static UsernamePasswordAuthenticationToken buildAuthentication(Claims jws) {
        String userName;
        String role;
        userName = jws.getSubject();

        //从token中获得角色信息
        role = jws.get("role", String.class);

        List<SimpleGrantedAuthority> roleList
                = Stream.of(Optional.ofNullable(role).orElse("").split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        //返回合法认证对象
        return new UsernamePasswordAuthenticationToken(userName, null, roleList);
    }

    /**
     * 生成 jwt token
     *
     * @param userName
     * @param role
     * @return
     */
    public static String generatorToken(String userName, String role) {
        //生成token
        String token = Jwts.builder()
                .setSubject(userName) //填写令牌的用户名
                .claim("role", role) //填写角色
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE)) //有效日期
                .signWith(SignatureAlgorithm.HS512, SECRET) //数字签名
                .compact();

        return token;
    }

    /**
     * 判断 token 是否要刷新
     *
     * @param token
     * @return true:该刷新了
     */
    public static boolean isRefress(Claims token) {
        return token.getExpiration().before(new Date(System.currentTimeMillis() + EXPECT));
    }
}
