package com.cloud.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @GetMapping("/user")
    public Object user(Principal user) {
        System.out.println("aaaaaaaaaaaa");
        return user;
    }
//    @GetMapping("/user")
//    public Object user() {
//        System.out.println("aaaaaaaaaaaa");
//        return null;
//    }
    /**
     * 清除Redis中 accesstoken refreshtoken
     * @param accesstoken  accesstoken
     * @param refreshToken refreshToken
     * @return true/false
     */
    @PostMapping("/removeToken")
    public Boolean removeToken(String accesstoken, String refreshToken) {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.removeRefreshToken(refreshToken);
        tokenStore.removeAccessToken(accesstoken);
        return Boolean.TRUE;
    }
}
