package com.app.ui.config;

import com.app.domain.car.Car;
import com.app.domain.car.type.Color;
import com.app.ui.config.type.ProfileType;
import org.springframework.context.annotation.*;
import org.springframework.context.support.AbstractApplicationContext;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@ComponentScan("com.app")
public class AppSpringConfig {

    /*@Bean
    public Gson gson() {
        return new GsonBuilder().setPrettyPrinting().create();

       }*/


    public static AbstractApplicationContext createContext(ProfileType profileType) {
        var context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles(profileType.toString());
        context.register(AppSpringConfig.class);
        context .refresh();
        return context;
    }



}
