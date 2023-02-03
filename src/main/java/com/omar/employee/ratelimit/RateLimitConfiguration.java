package com.omar.employee.ratelimit;


import es.moki.ratelimitj.core.limiter.request.RequestLimitRule;
import es.moki.ratelimitj.core.limiter.request.RequestRateLimiter;
import es.moki.ratelimitj.inmemory.request.InMemorySlidingWindowRequestRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Collections;
import java.util.Set;

@Configuration
public class RateLimitConfiguration{

    @Bean
    public RequestRateLimiter rateLimiterServiceConfig(){

        Set<RequestLimitRule> rules = Collections.singleton(
                RequestLimitRule.of(Duration.ofMinutes(1), 3)
        );

        return new InMemorySlidingWindowRequestRateLimiter(rules);
    }
}
