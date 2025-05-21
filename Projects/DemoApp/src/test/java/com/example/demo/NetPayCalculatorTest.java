package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NetPayCalculatorTest {

    @Autowired
    NetPayCalCulator netPayCalCulator;

    @Test
    public void calculateTaxTest() {
        double inputSalary = 300000D;
        double expectedSalary = 300000D;
        double actualSalary = netPayCalCulator.calculateNetSalary(inputSalary);
        Assertions.assertEquals(expectedSalary, actualSalary, 0.1);
    }
}
