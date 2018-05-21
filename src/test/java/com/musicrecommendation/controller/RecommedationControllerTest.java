package com.musicrecommendation.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.musicrecommendation.service.RecommendationService;
import java.util.Collection;
import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(RecommedationController.class)
public class RecommedationControllerTest {

    private static final String BASE_URL = "/recommendations";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecommendationService recommendationService;

    @Test
    public void shouldFindRecommendationsByCity() throws Exception {

        String city = "Campinas";

        Collection<String> recommendations = Collections.singleton("Music 1");

        when(recommendationService.findRecommendationsByCity(city)).thenReturn(recommendations);

        mockMvc.perform(get(BASE_URL .concat("?city={city}"), city))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)));

        verify(recommendationService, times(1)).findRecommendationsByCity(city);
    }
}