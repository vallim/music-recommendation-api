package com.musicrecommendation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "retry.config")
@Getter
@Setter
public class RetryTemplateConfig {

    private int maxAttempts;
    private int backOffPeriod;

}
