package com.musicrecommendation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TemperatureService {

    @Autowired
    private RestTemplate restTemplate;

    public String findTemperatureByCity(String city) {
        String url = "https://api.openweathermap.org/data/2.5/weather?units=metric&APPID=7210b0adeff8918aee50bbe9285690dd&q={city}";

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, city);

        return responseEntity.getBody();
    }
}
