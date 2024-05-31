package com.example.demo.service;

public class TaxSlabTenPercent implements TaxSlab{

    public static final double TAX_PERCENT = 0.1;

    @Override
    public double fetchTax(double salary) {
        return TAX_PERCENT * salary;
    }

    @Override
    public double fetchTaxWithFixedTax(double salary, double fixedTax, double baseSalary) {
        return fixedTax + TAX_PERCENT * (salary - baseSalary);
    }
}
