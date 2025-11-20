package com.example.jwt.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TestController {

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/getToken")
    public String generateToken(@RequestParam("username")String username){
        String token=jwtUtils.generateToken(username);
        String s = jwtUtils.extractUsernameFromToken(token);
        boolean b = jwtUtils.validateToken(token);
        Date expiration = jwtUtils.parseToken(token).getExpiration();
        System.out.println("expiration:"+expiration);
        System.out.println("username:"+s);
        System.out.println("validateToken:"+b);
        return token;
    }
}
