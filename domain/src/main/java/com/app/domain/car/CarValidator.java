package com.app.domain.car;

import com.app.domain.configs.validator.Validator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CarValidator implements Validator<Car> {
    @Override
    public Map<String, String> validate(Car car) {
        var errors = new HashMap<String,String>();

        if ( car == null ) {
            errors.put("car", "object is null");
            return errors;
        }

        var model = car.model;
        if (hasIncorrectModel(model)) {
            errors.put("model", "should contains only upper case letters");
        }

        var price = car.price;
        if (hasIncorrectPrice(price)) {
            errors.put("price","price should be greater than 0");
        }

        if(Objects.isNull(car.color)) {
            errors.put("color","color is null");
        }

        if(car.mileage < 0) {
            errors.put("mileage","mileage should be greater than 0");
        }

        if(car.components.size() == 0) {
            errors.put("components","components list cannot be empty");
        }

        return errors;
    }


    private boolean hasIncorrectModel(String model) {
        return model == null || !model.matches("[A-Z]+");
    }

    private boolean hasIncorrectPrice(BigDecimal price) {return price == null || price.compareTo(BigDecimal.ZERO) <= 0;}

}
