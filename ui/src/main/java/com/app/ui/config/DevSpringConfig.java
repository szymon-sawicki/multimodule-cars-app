package com.app.ui.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;


@Configuration
@Profile("dev")
@PropertySource("classpath:application-dev.properties")
@RequiredArgsConstructor

public class DevSpringConfig {

    private final Environment environment;


}
