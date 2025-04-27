package com.example.demo.service;

import org.springframework.stereotype.Component;

@Component
public interface TaxSlab {

    double fetchTax(double salary);

    default double fetchTaxWithFixedTax(double salary, double fixedTax, double baseSalary) {
        return 0;
    }
}
