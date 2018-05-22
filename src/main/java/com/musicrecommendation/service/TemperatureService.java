package com.musicrecommendation.service;

import com.musicrecommendation.config.TemperatureApiConfig;
import com.musicrecommendation.model.TemperatureContainerDto;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TemperatureService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TemperatureApiConfig temperatureApiConfig;

    @Autowired
    private RetryTemplate retryTemplate;

    public BigDecimal findTemperatureByCity(String city) {
        String url = temperatureApiConfig.getBaseURL().concat("?q={city}")
            .concat(temperatureApiConfig.getDefaultQueryParams());

        final TemperatureContainerDto containerDto = retryTemplate.execute(
            retryContext -> restTemplate
                .getForObject(url, TemperatureContainerDto.class, city));

        return containerDto.getMain().getTemp();
    }

    public BigDecimal findTemperatureByLatLong(String lat, String lon) {
        String url = temperatureApiConfig.getBaseURL().concat("?lat={lat}&lon={lon}")
            .concat(temperatureApiConfig.getDefaultQueryParams());

        TemperatureContainerDto containerDto = retryTemplate.execute(
            retryContext -> restTemplate
                .getForObject(url, TemperatureContainerDto.class, lat, lon));

        return containerDto.getMain().getTemp();
    }
}
