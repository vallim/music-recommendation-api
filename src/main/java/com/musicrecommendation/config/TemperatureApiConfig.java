package com.musicrecommendation.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties
@Setter
@Getter
public class TemperatureApiConfig {

    @Value("${temperature.api.baseURL}")
    private String baseURL;

}
