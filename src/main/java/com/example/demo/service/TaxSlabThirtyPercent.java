package com.example.demo.service;

public class TaxSlabThirtyPercent implements TaxSlab{

    @Override
    public double fetchTax(double salary) {
        return 0.3 * salary;
    }
}
