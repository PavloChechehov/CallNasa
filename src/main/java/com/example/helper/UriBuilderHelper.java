package com.example.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class UriBuilderHelper {

    private static final String SOL_PARAM = "sol";
    private static final String API_KEY_PARAM = "api_key";

    @Value("${nasa.url.photos}")
    private String nasaUrl;

    @Value("${nasa.api.key}")
    private String apiKey;

    public String buildUrl(Integer sol) {
        return UriComponentsBuilder.fromHttpUrl(nasaUrl)
            .queryParam(SOL_PARAM, sol)
            .queryParam(API_KEY_PARAM, apiKey)
            .build()
            .toString();
    }

}
