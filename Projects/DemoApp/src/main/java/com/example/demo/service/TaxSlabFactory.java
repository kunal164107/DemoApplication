package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaxSlabFactory {

    private static final double TAX_SALB_BELOW_5_LAKH = 500000D;
    private static final double TAX_SALB_BETWEEN_5_TO_10_LAKH = 1000000D;
    private static final double TAX_SALB_BETWEEN_10_TO_20_LAKH = 2000000D;
    private static final double TAX_SALB_AFTER_20_LAKH = 2000000D;


    @Autowired
    ZeroTaxSlab zeroTaxSlab;
    @Autowired
    TaxSlabTenPercent taxSlabTenPercent;
    @Autowired
    TaxSalbTwentyPercent taxSalbTwentyPercent;
    @Autowired
    TaxSlabThirtyPercent taxSlabThirtyPercent;

    private TaxSlab calculateTax(double salary) {
        if (salary <= TAX_SALB_BELOW_5_LAKH) {
            return zeroTaxSlab;
        } else if (salary > TAX_SALB_BELOW_5_LAKH && salary <= TAX_SALB_BETWEEN_5_TO_10_LAKH) {
            return taxSlabTenPercent;
        } else if (salary > TAX_SALB_BETWEEN_5_TO_10_LAKH && salary <= TAX_SALB_BETWEEN_10_TO_20_LAKH) {
            return taxSalbTwentyPercent;
        } else if (salary > TAX_SALB_AFTER_20_LAKH) {
            return taxSlabThirtyPercent;
        }
        return null;
    }
}
