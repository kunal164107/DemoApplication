package com.example.demo.service;

public class TaxSalbTwentyPercent implements TaxSlab{

    @Override
    public double fetchTax(double salary) {
        return 0.2 * salary;
    }
}
