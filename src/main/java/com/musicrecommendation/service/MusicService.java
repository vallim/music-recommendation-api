package com.musicrecommendation.service;

import com.musicrecommendation.model.MusicCategoryEnum;
import java.util.Collection;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MusicService {

    @Autowired
    private RestTemplate restTemplate;

    public Collection<String> findMusicsByCategory(MusicCategoryEnum musicCategory) {

        final String url = "https://api.spotify.com/v1/recommendations"
            .concat("?seed_genres={genre}");

        String response = restTemplate
            .getForObject(url, String.class, url, musicCategory.getValue());

        return Collections.emptyList();
    }
}
