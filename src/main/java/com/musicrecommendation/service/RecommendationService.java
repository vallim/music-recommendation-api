package com.musicrecommendation.service;

import java.util.Collection;
import java.util.Collections;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    public Collection<String> findRecommendationsByCity(String city) {
        return Collections.emptyList();
    }

    public Collection<String> findRecommendationsByLatLong(String lat, String lon) {
        return Collections.emptyList();
    }
}
