package org.example.service;

import org.example.check.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Service;

@Service("MetricsImpl")
public class MetricsImpl implements Metrics {

    @Autowired
    @Qualifier("CheckImpl")
    private Check check;

    @Autowired
    @Qualifier("CalculationFactorial")
    private Calculation calculation;

    Health metrica;

    private long timeCheck;
    private long timeCalculation;
    private long timeCheckResult;
    private long timeCalculationResult;

    @Override
    public Health health() {
        metrica = Health.up()
                .withDetail("checkTime", timeCheckResult)
                .withDetail("calculationTime", timeCalculationResult)
                .build();
        return metrica;
    }

    @Override
    public Health getMetrics(Double number) {

        timeCheck = System.currentTimeMillis();
        check.getCheck(number);
        timeCheckResult = System.currentTimeMillis() - timeCheck;

        timeCalculation = System.currentTimeMillis();
        calculation.calculationResult(number);
        timeCalculationResult = System.currentTimeMillis() - timeCalculation;

        return health();
    }

}
