package com.app.ui;


import com.app.domain.car.Car;
import com.app.service.CarsService;
import com.app.ui.config.AppSpringConfig;
import com.app.ui.config.TestSpringConfig;
import com.app.ui.config.type.ProfileType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import spark.Spark;

import java.util.Arrays;


public class App {

    public static void main(String[] args) {

        var context = AppSpringConfig.createContext(ProfileType.DEV);
        var carService = context.getBean("carsService", CarsService.class);
        var routing = context.getBean("routing",Routing.class);

        Spark.initExceptionHandler(e->e.getMessage());
        Spark.port(8000);

        routing.routes();


    }
}
