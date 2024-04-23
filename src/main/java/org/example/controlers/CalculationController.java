package org.example.controlers;

import org.example.check.Check;
import org.example.pojo.FactorialNumber;
import org.example.pojo.ResultFactorialNumber;
import org.example.service.Calculation;
import org.example.service.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculationController {
    @Autowired
    @Qualifier("CheckImpl")
    Check check;

    @Autowired
    @Qualifier("CalculationFactorial")
    Calculation calculation;

    @Autowired
    @Qualifier("MetricsImpl")
    Metrics metrics;

    @PostMapping("/factorial")
    public ResultFactorialNumber factorial(@RequestBody FactorialNumber request) {
        Double number = request.getFactorial_num();
        if (check.getCheck(number)) {
            Double result = calculation.calculationResult(number);
            return new ResultFactorialNumber(result, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/metrics")
    public Health metrics(@RequestParam("factorial_num") Double factorial_num) {
        return metrics.getMetrics(factorial_num);
    }

}
