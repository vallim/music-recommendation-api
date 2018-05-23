package com.musicrecommendation.interceptor;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {

    private static final Set<String> FORBIDDEN_HEADERS = Collections.singleton("Authorization");

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body,
        ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {

        logRequest(httpRequest, body);

        ClientHttpResponse response = clientHttpRequestExecution.execute(httpRequest, body);

        logResponse(response);

        return response;
    }

    private static void logRequest(HttpRequest request, byte[] body) throws IOException {
        log.debug("URI         : {}", request.getURI());
        log.debug("Method      : {}", request.getMethod());
        log.debug("Headers     : {}", sanitizeHeader(request.getHeaders()));
        log.debug("Request Body: {}", new String(body, "UTF-8"));
    }

    private static void logResponse(ClientHttpResponse response) throws IOException {
        log.debug("Status code  : {}", response.getStatusCode());
        log.debug("Status text  : {}", response.getStatusText());
        log.debug("Headers      : {}", sanitizeHeader(response.getHeaders()));
        log.debug("Response body: {}", response.getBody());
    }

    private static Map sanitizeHeader(HttpHeaders headers) {

        final Map<String, Object> sanitizedHeader = new LinkedHashMap<>();

        for (Entry<String, List<String>> header : headers.entrySet()) {
            if (!FORBIDDEN_HEADERS.contains(header.getKey())) {
                sanitizedHeader.put(header.getKey(), header.getValue());
            }
        }

        return sanitizedHeader;
    }
}
