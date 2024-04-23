package org.example.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public interface Metrics extends HealthIndicator {
   public Health getMetrics (Double number);

}
