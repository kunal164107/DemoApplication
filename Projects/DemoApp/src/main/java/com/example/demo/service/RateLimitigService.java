package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitigService {

    Map<String, Window> requestMap = new ConcurrentHashMap<>();

    public boolean isAllowed(String identifier) {
        long now = System.currentTimeMillis();
        System.out.println("now" + now);
        long currentWindowStart = (now / RateLimitConfig.timeWindow) * RateLimitConfig.timeWindow;
        System.out.println("currentWindowStart" + currentWindowStart);

        Window info = requestMap.getOrDefault(identifier, new Window(currentWindowStart, 0));
        System.out.println("info ws" + info.windowStart);
        System.out.println("info count" + info.count);
        if (info.windowStart == currentWindowStart) {
            if (info.count < RateLimitConfig.maxRequest) {
                info.count++;
                requestMap.put(identifier, info);
                return true;
            } else {
                return false;
            }
        } else {
            // reset window
            info.windowStart = currentWindowStart;
            info.count = 1;
            requestMap.put(identifier, info);
            return true;
        }
    }









}
