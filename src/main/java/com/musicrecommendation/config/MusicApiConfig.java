package com.musicrecommendation.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "music.api")
@Getter
@Setter
public class MusicApiConfig {

    private String baseUrl;
    private String recommendationURI;
}
