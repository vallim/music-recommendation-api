package com.musicrecommendation.controller;

import com.musicrecommendation.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/recommendations")
@RestController
public class RecommedationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping
    public ResponseEntity getRecommendations() {

        return ResponseEntity.ok().build();
    }
}
