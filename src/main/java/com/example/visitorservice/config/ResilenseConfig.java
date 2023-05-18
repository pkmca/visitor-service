package com.example.visitorservice.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.Duration;

@Configuration
public class ResilenseConfig {

    @Bean
  public CircuitBreakerConfig getConfig() {
      CircuitBreakerConfig config = CircuitBreakerConfig.custom()
              .failureRateThreshold(50) // Failure rate threshold percentage
              .waitDurationInOpenState(Duration.ofMillis(1000)) // Time to wait before transitioning from OPEN to HALF_OPEN state
              .ringBufferSizeInHalfOpenState(10) // Number of permitted calls in HALF_OPEN state
              .ringBufferSizeInClosedState(100) // Number of permitted calls in CLOSED state
              .build();
      return config;
  }


}
