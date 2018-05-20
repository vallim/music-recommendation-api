package com.musicrecommendation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/recommendations")
@RestController
public class RecommedationController {

    @GetMapping
    public ResponseEntity getRecommendations() {

        return ResponseEntity.ok().build();
    }
}
