package com.musicrecommendation.service;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.junit.Assert.assertTrue;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import java.math.BigDecimal;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TemperatureServiceTest {

    private static final BigDecimal _25_DEGREES_CELSIUS = new BigDecimal("25");

    @ClassRule
    public static WireMockRule wireMockRule = new WireMockRule(
        WireMockConfiguration.wireMockConfig().port(8089));

    @Autowired
    private TemperatureService temperatureService;

    @Test
    public void shouldReturnATemperatureGivenACity() {
        String city = "Campinas";

        stubFor(get(urlPathEqualTo("/&q=".concat(city)))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"main\":{\"temp\":\"25\"}}")));

        BigDecimal response = temperatureService.findTemperatureByCity(city);

        assertTrue(_25_DEGREES_CELSIUS.compareTo(response) == 0);
    }
}
