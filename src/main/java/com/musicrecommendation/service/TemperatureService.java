package com.musicrecommendation.service;

import org.springframework.stereotype.Service;

@Service
public class TemperatureService {

    public String findTemperatureByCity(String city) {
        return "20";
    }
}
