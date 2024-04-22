package org.example.service;

import org.apache.commons.math3.special.Gamma;
import org.springframework.stereotype.Service;

@Service("CalculationFactorial")
public class CalculationFactorial implements Calculation {
    @Override
    public double calculationResult(double number) {
        if (number == 0) return number;
        if (number == 1) return number;
        double result = 1.;

        if(number != (int) number ){
            result = Gamma.gamma(number + 1);
            return result;
        }

        for (double i = result + 1.; i <= number; i++) {
            result = result * i;
        }
        return result;
    }
}
