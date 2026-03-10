package com.mamun.apiplatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RateLimiterService {

    private final StringRedisTemplate redisTemplate;

    public boolean isAllowed(String apiKey, int limit) {

        String redisKey = "rate_limit:" + apiKey;

        Long currentCount = redisTemplate.opsForValue().increment(redisKey);

        if (currentCount == 1) {
            redisTemplate.expire(redisKey, Duration.ofMinutes(1));
        }

        return currentCount <= limit;
    }
}