package com.musicrecommendation.service;

import java.util.Collection;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    @Autowired
    private TemperatureService temperatureService;

    public Collection<String> findRecommendationsByCity(String city) {
        String temperature = temperatureService.findTemperatureByCity(city);

        return Collections.emptyList();
    }

    public Collection<String> findRecommendationsByLatLong(String lat, String lon) {
        return Collections.emptyList();
    }
}
