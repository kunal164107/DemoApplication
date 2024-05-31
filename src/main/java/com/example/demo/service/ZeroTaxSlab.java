package com.example.demo.service;

public class ZeroTaxSlab implements TaxSlab{

    @Override
    public double fetchTax(double salary) {
        return 0 * salary;
    }
}
