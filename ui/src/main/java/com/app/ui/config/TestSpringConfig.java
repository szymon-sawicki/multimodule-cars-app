package com.app.ui.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;



@Configuration
@Profile("test")
@PropertySource("classpath:application-test.properties")
@RequiredArgsConstructor

public class TestSpringConfig {

    private final Environment environment;

}
