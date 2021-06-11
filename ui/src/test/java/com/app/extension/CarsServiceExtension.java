package com.app.extension;


import com.app.service.CarsService;
import com.app.ui.config.AppSpringConfig;
import com.app.ui.config.type.ProfileType;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;


public class CarsServiceExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CarsService.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        /*AbstractApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("test");
        // ....
        return context.getBean("carService", CarsService.class);*/

       var context = AppSpringConfig.createContext(ProfileType.TEST);
       return context.getBean("carsService", CarsService.class);


/*
        var context = new AnnotationConfigApplicationContext();
       // context.getEnvironment().setActiveProfiles("test");
        context.register(TestAppSpringConfig.class);
        context.refresh();
        return context.getBean("carsService",CarsService.class);
*/




    }
        }


