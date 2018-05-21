package com.musicrecommendation.service;

import com.musicrecommendation.config.MusicTokenConfig;
import com.musicrecommendation.model.MusicTokenDto;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class MusicTokenService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MusicTokenConfig musicTokenConfig;

    public String getAccessToken() {

        final MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("grant_type", "client_credentials");

        final HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(multiValueMap,
            headers());

        final MusicTokenDto tokenDto = restTemplate
            .postForObject(musicTokenConfig.getBaseURL(), httpEntity, MusicTokenDto.class);

        return tokenDto.getAccessToken();
    }

    private HttpHeaders headers() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add(HttpHeaders.AUTHORIZATION, getAuthorization());

        return headers;
    }

    private String getAuthorization() {
        final String authorizationKey = musicTokenConfig.getClientID().concat(":")
            .concat(musicTokenConfig.getClientSecret());

        return "Basic ".concat(Base64.getEncoder().encodeToString(authorizationKey.getBytes()));
    }
}
