package com.musicrecommendation.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "music.token.api")
@Getter
@Setter
public class MusicTokenApiConfig {

    private String baseURL;
    private String clientID;
    private String clientSecret;
}

