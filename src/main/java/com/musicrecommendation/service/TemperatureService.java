package com.musicrecommendation.service;

import com.musicrecommendation.config.TemperatureApiConfig;
import com.musicrecommendation.model.TemperatureContainerDto;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TemperatureService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TemperatureApiConfig temperatureApiConfig;

    public BigDecimal findTemperatureByCity(String city) {
        String url = temperatureApiConfig.getBaseURL().concat("&q={city}");

        final TemperatureContainerDto containerDto = restTemplate
            .getForObject(url, TemperatureContainerDto.class, city);

        return containerDto.getMain().getTemp();
    }

    public BigDecimal findTemperatureByLatLong(String lat, String lon) {
        String url = temperatureApiConfig.getBaseURL().concat("&lat={lat}&lon={lon}");

        TemperatureContainerDto containerDto = restTemplate
            .getForObject(url, TemperatureContainerDto.class, lat, lon);

        return containerDto.getMain().getTemp();
    }
}
