package com.example.demo.controller;

import com.example.demo.apimodels.RouteCalculatorRequest;
import com.example.demo.service.OptimalRouteCalculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/application")
@Slf4j
public class RouteController {

    @Autowired
    private OptimalRouteCalculator optimalRouteCalculator;

    @PostMapping("/find-route")
    public ResponseEntity<String> calculateOptimalRoute(@RequestBody @Validated RouteCalculatorRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(optimalRouteCalculator.calculateRoute(request));
    }
}
