package org.example.controlers;

import org.example.check.Check;
import org.example.pojo.FactorialNumber;
import org.example.pojo.ResultFactorialNumber;
import org.example.service.Calculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/calculator")
public class CalculationController {
    @Autowired
    @Qualifier("CheckImpl")
    Check check;

    @Autowired
    @Qualifier("CalculationFactorial")
    Calculation calculation;
    @PostMapping("/factorial")
    public ResultFactorialNumber factorial(@RequestBody FactorialNumber request) {
        Double number = request.getFactorial_num();
       if (check.getCheck(number)){
           Double result = calculation.calculationResult(number);
           return new ResultFactorialNumber(result, HttpStatus.OK);
       }
       return null;

    }
}
