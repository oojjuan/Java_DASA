package com.cp.dasa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ConnectionFactory();
    }
}
