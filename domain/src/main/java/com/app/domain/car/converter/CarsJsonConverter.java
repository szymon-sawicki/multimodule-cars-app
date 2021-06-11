package com.app.domain.car.converter;

import com.app.domain.car.Car;
import com.app.domain.configs.converter.JsonConverter;

import java.util.List;

public class CarsJsonConverter extends JsonConverter<List<Car>> {
    public CarsJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
