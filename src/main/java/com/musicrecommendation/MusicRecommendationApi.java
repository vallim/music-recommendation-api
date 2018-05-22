package com.musicrecommendation;

import com.musicrecommendation.config.ApiTimeoutConfig;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MusicRecommendationApi {

	public static void main(String[] args) {
		SpringApplication.run(MusicRecommendationApi.class, args);
	}

	@Autowired
	private RetryTemplateConfig retryTemplateConfig;

	@Autowired
	private ApiTimeoutConfig apiTimeoutConfig;

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		SimpleClientHttpRequestFactory requestFactory = (SimpleClientHttpRequestFactory) restTemplate
			.getRequestFactory();
		requestFactory.setConnectTimeout(apiTimeoutConfig.getTimeout());
		requestFactory.setReadTimeout(apiTimeoutConfig.getTimeout());

		return restTemplate;
	}

	@Bean
	public RetryTemplate retryTemplate() {
		final Map<Class<? extends Throwable>, Boolean> retryableExceptions = new HashMap<>();
		retryableExceptions.put(ResourceAccessException.class, true);
		retryableExceptions.put(SocketTimeoutException.class, true);

		final SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy(retryTemplateConfig.getMaxAttempts(), retryableExceptions);

		final FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
		backOffPolicy.setBackOffPeriod(retryTemplateConfig.getBackOffPeriod());

		RetryTemplate retryTemplate = new RetryTemplate();
		retryTemplate.setRetryPolicy(retryPolicy);
		retryTemplate.setBackOffPolicy(backOffPolicy);

		return retryTemplate;
	}
}
