package com.musicrecommendation.service;

import com.musicrecommendation.config.TemperatureApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TemperatureService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TemperatureApiConfig temperatureApiConfig;

    public String findTemperatureByCity(String city) {
        String url = temperatureApiConfig.getBaseURL().concat("&q={city}");

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, city);

        return responseEntity.getBody();
    }
}
