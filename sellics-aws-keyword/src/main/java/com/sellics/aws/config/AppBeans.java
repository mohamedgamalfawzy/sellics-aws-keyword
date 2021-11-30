package com.sellics.aws.config;

import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class AppBeans {
    @Bean
    public RestTemplate initRestTemplate(){
        return new RestTemplate();
    }
    @Bean
    public TimeLimiter initTimeLimiter(){
       return TimeLimiter.of(TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(10)).build());
    }
}
