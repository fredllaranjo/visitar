package com.gmail.fredllaranjo.visitarapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gmail.fredllaranjo.visitarapi.api.exception.mappers.KnownExceptionsMapper;

/**
 * Bean configuration for Rest API module.
 * 
 * @author fredllaranjo
 */
@Configuration
public class RestApiConfiguration {

    @Bean
    public KnownExceptionsMapper knownExceptionMapper() {
        return new KnownExceptionsMapper();
    }
}
