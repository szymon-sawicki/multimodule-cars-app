package com.app.domain.car.converter;

import com.app.domain.car.Car;
import com.app.domain.configs.converter.JsonConverter;

import java.util.List;

/**
 * child class of JsonConverter used to converting list of cars
 * @author Szymon Sawicki
 */

public class CarsJsonConverter extends JsonConverter<List<Car>> {
    public CarsJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
