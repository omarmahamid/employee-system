package com.omar.employee.ratelimit;


import es.moki.ratelimitj.core.limiter.request.RequestRateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestRateLimitService implements IRateLimitService{


    private final RequestRateLimiter requestRateLimiter;

    public RequestRateLimitService(@Autowired RequestRateLimiter requestRateLimiter){
        this.requestRateLimiter = requestRateLimiter;
    }

    @Override
    public boolean overLimit() {
        return requestRateLimiter.overLimitWhenIncremented("ip:127.0.0.1");
    }
}
