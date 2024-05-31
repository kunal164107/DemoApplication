package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class NetPayCalCulator {

    /*
        Design a net pay calculator for a payroll system.

        Net pay is the amount remaining after income tax is deducted from the salary of an employee.
        The calculator takes the salary of the employee as an input and deducts the taxes based on
        predefined tax slabs to calculate the net pay.

        Note that the tax slabs are subject to frequent changes and hence the system needs to be easily
        configurable to adapt these changes without or with minimal change in the codebase.

        Implement this using object oriented programming and write unit tests for the same.


        upto 5 lac - 0
        5-10       - 10
        10-20      - 20
        20 +       - 30

        Follow-up

        upto 5 lac - 0
        5-10       - 10K + 10% on extra i.e if salary is 7 lac then 10K + 10% on 2 lac
        10-20      - 20K + 20% on extra
        20 +       - 30K + 30% on extra

         */

    private static final double TAX_SALB_BELOW_5_LAKH = 500000D;
    private static final double TAX_SALB_BETWEEN_5_TO_10_LAKH = 1000000D;
    private static final double TAX_SALB_BETWEEN_10_TO_20_LAKH = 2000000D;
    private static final double TAX_SALB_AFTER_20_LAKH = 2000000D;

    public Double calculateNetSalary(double salary) {
        double tax = calculateTax(salary);
        return salary - tax;
    }

    private double calculateTax(double salary) {
        if (salary <= TAX_SALB_BELOW_5_LAKH) {
            return 0;
        } else if (salary > TAX_SALB_BELOW_5_LAKH && salary <= TAX_SALB_BETWEEN_5_TO_10_LAKH) {
            return 0.1 * salary;
        } else if (salary > TAX_SALB_BETWEEN_5_TO_10_LAKH && salary <= TAX_SALB_BETWEEN_10_TO_20_LAKH) {
            return 0.2 * salary;
        } else if (salary > TAX_SALB_AFTER_20_LAKH) {
            return 0.3 * salary;
        }
        return 0;
    }
}
