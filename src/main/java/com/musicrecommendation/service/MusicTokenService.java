package com.musicrecommendation.service;

import com.musicrecommendation.config.MusicTokenApiConfig;
import com.musicrecommendation.model.MusicTokenDto;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class MusicTokenService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MusicTokenApiConfig musicTokenApiConfig;

    @Autowired
    private RetryTemplate retryTemplate;

    @Cacheable(value = "musicAccessToken")
    public String getAccessToken() {

        final MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("grant_type", "client_credentials");

        final HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap,
            headers());

        final MusicTokenDto tokenDto = retryTemplate.execute(
            retryContext -> restTemplate
            .postForObject(musicTokenApiConfig.getBaseURL(), httpEntity, MusicTokenDto.class));

        return tokenDto.getAccessToken();
    }

    private HttpHeaders headers() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(HttpHeaders.AUTHORIZATION, getAuthorization());

        return headers;
    }

    private String getAuthorization() {
        final String authorizationKey = musicTokenApiConfig.getClientID().concat(":")
            .concat(musicTokenApiConfig.getClientSecret());

        return "Basic ".concat(Base64.getEncoder().encodeToString(authorizationKey.getBytes()));
    }
}
