package com.musicrecommendation.controller;

import com.musicrecommendation.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getRecommendations(@RequestParam(name = "city", required = false) String city) {

        recommendationService.findRecommendationsByCity(city);

        return ResponseEntity.ok().build();
    }
}
