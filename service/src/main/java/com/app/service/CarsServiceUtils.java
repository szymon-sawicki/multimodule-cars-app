package com.app.service;

import com.app.domain.car.Car;

import java.util.List;
import java.util.function.Function;

public interface CarsServiceUtils {

    Function<CarsService, List<Car>> toCars = carsService -> carsService.cars;

}
