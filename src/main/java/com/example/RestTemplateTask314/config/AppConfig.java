package com.example.RestTemplateTask314.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate gerRestTemplate() {
        return new RestTemplate();
    }
}
