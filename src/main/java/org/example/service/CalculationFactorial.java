package org.example.service;

import org.apache.commons.math3.special.Gamma;
import org.springframework.stereotype.Service;

@Service("CalculationFactorial")
public class CalculationFactorial implements Calculation {
    @Override
    public double calculationResult(double number) {
        if (number == 0) return number;
        if (number == 1) return number;
        return Gamma.gamma(number + 1);
    }
}
