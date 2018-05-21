package com.musicrecommendation.service;

import com.musicrecommendation.model.MusicCategoryEnum;
import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    @Autowired
    private TemperatureService temperatureService;

    @Autowired
    private MusicService musicService;

    public Collection<String> findRecommendationsByCity(String city) {
        BigDecimal temperature = temperatureService.findTemperatureByCity(city);

        return find(temperature);
    }

    public Collection<String> findRecommendationsByLatLong(String lat, String lon) {
        BigDecimal temperatureByLatLong = temperatureService.findTemperatureByLatLong(lat, lon);

        return find(temperatureByLatLong);
    }

    private Collection<String> find(BigDecimal temperature) {
        final MusicCategoryEnum musicCategory = MusicCategoryEnum.find(temperature);

        return musicService.findTracksByCategory(musicCategory);
    }
}
