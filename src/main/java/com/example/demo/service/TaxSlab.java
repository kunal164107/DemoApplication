package com.example.demo.service;

public interface TaxSlab {

    double fetchTax(double salary);

    default double fetchTaxWithFixedTax(double salary, double fixedTax, double baseSalary) {
        return 0;
    }
}
