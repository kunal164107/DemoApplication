package com.example.demo.controller;

import com.example.demo.service.RateLimitigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class RateLimitcontroller {

    private RateLimitigService rateLimitigService;

    public RateLimitcontroller(RateLimitigService rateLimitigService) {
        this.rateLimitigService = rateLimitigService;
    }

    @PostMapping("/request")
    public void rateLimit(@RequestParam String identifier) {
        if (identifier == null || identifier.isEmpty()) {
            System.out.println("Invalid request");
        }

        boolean isAllowed = rateLimitigService.isAllowed(identifier);
        if (isAllowed) {
            System.out.println("Request Allowed");
        } else {
            System.out.println("Request Denied");
        }
    }


}
