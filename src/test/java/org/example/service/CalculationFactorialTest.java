package org.example.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationFactorialTest {

    private CalculationFactorial calculationFactorial = new CalculationFactorial();

    @Test
    void CalculationGoodResult() throws Exception {
        Double factorial = 16.;
        Double result = 20922789888000.;
        Assertions.assertEquals(result, calculationFactorial.calculationResult(factorial));
    }

    @Test
    void CalculationFactorialIsDouble() throws Exception {
        Double factorial = 16.5;
        Double result = 8.563497447516206E13;
        Assertions.assertEquals(result, calculationFactorial.calculationResult(factorial));
    }

    @Test
    void CalculationFactorialIsMaxNumber() throws Exception {
        Double factorial = 100.;
        Double result = 9.332621544394413E157;
        Assertions.assertEquals(result, calculationFactorial.calculationResult(factorial));
    }

    @Test
    void CalculationFactorialIs0() throws Exception {
        Double factorial = 0.;
        Double result = 0.;
        Assertions.assertEquals(result, calculationFactorial.calculationResult(factorial));
    }

    @Test
    void CalculationFactorialIs1() throws Exception {
        Double factorial = 1.;
        Double result = 1.;
        Assertions.assertEquals(result, calculationFactorial.calculationResult(factorial));
    }
}