package com.musicrecommendation.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "temperature.api")
@Setter
@Getter
public class TemperatureApiConfig {

    private String baseURL;
    private String defaultQueryParams;

}
