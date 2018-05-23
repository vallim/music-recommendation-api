package com.musicrecommendation.service;

import static org.junit.Assert.assertEquals;

import com.musicrecommendation.config.TemperatureApiConfig;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemperatureServiceCacheTest {

    @Autowired
    private TemperatureService temperatureService;

    @Test
    public void shouldPutFindTemperatureByCityInCache() {
        String city = "Campinas";

        final BigDecimal temperatureOne = new BigDecimal("13");
        final BigDecimal temperatureTwo = new BigDecimal("21");

        Mockito.when(temperatureService.findTemperatureByCity(Mockito.anyString())).thenReturn(temperatureOne, temperatureTwo);

        BigDecimal firstCall = temperatureService.findTemperatureByCity(city);
        assertEquals(temperatureOne, firstCall);

        BigDecimal secondCall = temperatureService.findTemperatureByCity(city);
        assertEquals(temperatureOne, secondCall);

        Mockito.verify(temperatureService, Mockito.times(1)).findTemperatureByCity(Mockito.anyString());
    }

    @EnableCaching
    @Configuration
    static class CacheConfig {

        @Bean
        TemperatureService colorService() {
            return Mockito.mock(TemperatureService.class);
        }

        @Bean
        CacheManager cacheManager() {
            return new ConcurrentMapCacheManager("temperatureByCity");
        }

        @Bean
        RestTemplate restTemplate() {
            return Mockito.mock(RestTemplate.class);
        }

        @Bean
        TemperatureApiConfig temperatureApiConfig() {
            return Mockito.mock(TemperatureApiConfig.class);
        }

        @Bean
        RetryTemplate retryTemplate() {
            return Mockito.mock(RetryTemplate.class);
        }
    }
}
