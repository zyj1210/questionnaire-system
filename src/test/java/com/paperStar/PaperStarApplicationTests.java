package com.paperStar;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class PaperStarApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void genjwt(){
        Map<String,Object> claims =new HashMap<>();
        claims.put("id",1);
        claims.put("username","Tom");
        String jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256,"PaperStarAdministratorTenGroupABKIQJFNLSHDION")
                .setExpiration(new Date(System.currentTimeMillis()+12*3600*1000))
                .compact();

        System.out.println(jwt);
    }

    @Test
    public void getJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("PaperStarAdministratorTenGroupABKIQJFNLSHDION")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJUb20iLCJleHAiOjE2ODU1NzkxMjF9.C8VYgGYL5RPuYayaw64RKYXi0i-CZMA2nWcQDn-q7xQ")
                .getBody();

        System.out.println(claims);
    }
}
