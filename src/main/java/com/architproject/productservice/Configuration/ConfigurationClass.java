package com.architproject.productservice.Configuration;


import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConfigurationClass {


    @Bean("webClientObj")
    public WebClient getWebClient() {
        return WebClient.create();
    }

    @Bean("restTemplateObj")
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
