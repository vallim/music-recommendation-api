package com.musicrecommendation.service;

import com.musicrecommendation.config.MusicApiConfig;
import com.musicrecommendation.model.MusicCategoryEnum;
import com.musicrecommendation.model.TrackContainerDto;
import com.musicrecommendation.model.TrackDto;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MusicService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MusicTokenService musicTokenService;

    @Autowired
    private MusicApiConfig musicApiConfig;

    @Autowired
    private RetryTemplate retryTemplate;

    public Collection<String> findTracksByCategory(MusicCategoryEnum musicCategory) {

        final String url = musicApiConfig.getBaseUrl()
            .concat(musicApiConfig.getRecommendationURI())
            .concat("?seed_genres=")
            .concat(musicCategory.getValue());

        final HttpEntity entity = new HttpEntity(headers());

        final ResponseEntity<TrackContainerDto> responseEntity = retryTemplate.execute(
            retryContext -> restTemplate
            .exchange(url, HttpMethod.GET, entity,
                TrackContainerDto.class));

        return responseEntity.getBody().getTracks().stream().map(TrackDto::getName).collect(
            Collectors.toList());
    }

    private HttpHeaders headers() {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION,
            "Bearer ".concat(musicTokenService.getAccessToken()));

        return headers;
    }
}
