package com.app.domain.car;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public interface CarUtil {
    Comparator<Car> compareByModel = Comparator.comparing(car -> car.model);
    Comparator<Car> compareByMileage = Comparator.comparing(car -> car.mileage);
    Comparator<Car> compareByPrice = Comparator.comparing(car -> car.price);
    Comparator<Car> compareByColor = Comparator.comparing(car -> car.color);

    Function<Car, List<String>> toComponents = car -> car.components;
}
