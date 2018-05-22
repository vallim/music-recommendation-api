package com.musicrecommendation;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
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
