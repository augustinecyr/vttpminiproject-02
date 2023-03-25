package com.sg.backend.repositories;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OAuth2Repository {
    @Autowired
    @Qualifier("redislab")
    private RedisTemplate<String, String> redisTemplate;
    // save the accesstoken by the oauth exchange
    public void saveAccessToken(String accessToken) {
        redisTemplate.opsForValue().set(accessToken, accessToken);
        redisTemplate.expire(accessToken, Duration.ofHours(10));
    }
    
}
