package com.musicrecommendation.service;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.musicrecommendation.model.MusicCategoryEnum.POP;
import static org.junit.Assert.assertTrue;

import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.musicrecommendation.model.MusicTokenDto;
import com.musicrecommendation.model.TrackContainerDto;
import com.musicrecommendation.model.TrackDto;
import com.musicrecommendation.util.JsonUtil;
import java.util.Collection;
import java.util.Collections;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MusicServiceTest {

    @Autowired
    private MusicService musicService;

    @ClassRule
    public static WireMockRule wireMockRule = new WireMockRule(
        WireMockConfiguration.wireMockConfig().port(8089).notifier(new ConsoleNotifier(true)));

    @Test
    public void shouldGetMusicsByCategory() {

        stubFor(post(urlPathEqualTo("/"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(200)
                .withBody(
                    JsonUtil
                        .toJson(MusicTokenDto.builder().accessToken("fakeAccessToken").build()))));

        stubFor(get(urlPathEqualTo("/recommendations"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withStatus(200)
                .withBody(JsonUtil.toJson(
                    TrackContainerDto.builder().tracks(Collections.singleton(new TrackDto()))
                        .build()))));

        Collection<String> popMusics = musicService.findTracksByCategory(POP);

        assertTrue(popMusics.size() == 1);
    }
}
