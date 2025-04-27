package com.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public class TaxSlabThirtyPercent implements TaxSlab{

    @Override
    public double fetchTax(double salary) {
        return 0.3 * salary;
    }
}
