package com.musicrecommendation.controller;

import com.musicrecommendation.service.RecommendationService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/recommendations")
@RestController
public class RecommedationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping
    public ResponseEntity getRecommendations(
        @RequestParam(name = "city", required = false) String city,
        @RequestParam(name = "lat", required = false) String lat,
        @RequestParam(name = "long", required = false) String lon) {

        Collection<String> recommendations;

        if (!StringUtils.isEmpty(city)) {
            recommendations = recommendationService.findRecommendationsByCity(city);
        } else if (!StringUtils.isEmpty(lat) && !StringUtils.isEmpty(lat)) {
            recommendations = recommendationService.findRecommendationsByLatLong(lat, lon);
        } else {
            throw new IllegalArgumentException("It is required to send city or latitude/longitude to get recommendations");
        }

        return ResponseEntity.ok(recommendations);
    }
}
