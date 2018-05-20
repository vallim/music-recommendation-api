package com.musicrecommendation.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    @Autowired
    private TemperatureService temperatureService;

    public Collection<String> findRecommendationsByCity(String city) {
        BigDecimal temperature = temperatureService.findTemperatureByCity(city);

        System.out.println(temperature);

        return Collections.emptyList();
    }

    public Collection<String> findRecommendationsByLatLong(String lat, String lon) {
        BigDecimal temperatureByLatLong = temperatureService.findTemperatureByLatLong(lat, lon);

        return Collections.emptyList();
    }
}
