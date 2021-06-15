package com.app.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.app.domain.car.Car;
import com.app.domain.car.CarUtil;
import com.app.domain.car.CarValidator;
import com.app.domain.car.converter.CarsJsonConverter;
import com.app.domain.configs.validator.Validator;
import com.app.service.exception.CarsServiceException;
import com.app.service.type.SortItem;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.stream.Collectors;

import static java.util.Collections.*;
import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;
import static com.app.domain.car.CarUtil.*;

/**
 * Main service class used to manage collection of cars. Cars are loaded from json file located under path in class field.
 * @author Szymon Sawicki
 */

@Service
public class CarsService {

    @Value("${filename.cars-1}")
    private String filename;

    List<Car> cars;


   public List<Car> getCars() {
        return cars;
    }

    /**
     * initialization method that take filepath from class field and converts data from json file to list of cars
     * and assign it to cars field
     */

    @PostConstruct
    private void init() {
       // >> java -jar --enable-preview ui/target/ui.jar
        this.cars = new CarsJsonConverter(filename)
                .fromJson()
                .orElseThrow(() -> new CarsServiceException("Cannot parse json file"))
                .stream()
                .peek(car -> Validator.validate(new CarValidator(), car))
                .collect(toList());

    }

    /**
     *
     * @return list of all cars
     */

    public List<Car> getAllCars() {
        return cars;
    }

    /**
     * @param sortItem   cryterium of sorting (enum)
     * @param descending direction of sorting
     * @return sorted list of cars
     */
    public List<Car> sort(SortItem sortItem, boolean descending) {

        if (sortItem == null) {
            throw new CarsServiceException("Sort item is null");
        }

        var carsStream = switch (sortItem) {
            case COLOR -> cars.stream().sorted(compareByColor);
            case MODEL -> cars.stream().sorted(compareByModel);
            case PRICE -> cars.stream().sorted(compareByPrice);
            case MILEAGE -> cars.stream().sorted(compareByMileage);
        };

        var sortedCars = carsStream.collect(toList());

        if (descending) {
            reverse(sortedCars);
        }

        return sortedCars;

    }


    /**
     * @param mileage min mileage value
     * @return list of cars with mileage greater than argument
     */
    public List<Car> findCarsWithMileageGreaterThan(int mileage) {

        if (mileage <= 0) {
            throw new CarsServiceException("Mileage is out of range");
        }

        return cars.stream()
                .filter(car -> car.hasMileageGreaterThan(mileage))
                .collect(toList());
    }


    /**
     * @param priceFrom
     * @param priceTo
     * @return all cars with mileage in given range
     */
    public List<Car> findCarsWithPriceInRange(BigDecimal priceFrom, BigDecimal priceTo) {

        if (priceFrom == null) {
            throw new CarsServiceException("Price from is null");
        }

        if (priceTo == null) {
            throw new CarsServiceException("Price to is null");
        }

        if (priceFrom.compareTo(priceTo) >= 0) {
            throw new CarsServiceException("Incorrect price range");
        }

        return cars.stream()
                .filter(car -> car.hasPriceInRange(priceFrom, priceTo))
                .collect(toList());
    }


    /**
     * @return list of all cars, with sorted components
     */
    public List<Car> carsWithSortedComponents() {
        return cars
                .stream()
                .map(Car::withSortedComponents)
                .collect(toList());
    }


    /**
     * @return map with component as key and list of cars having that component
     */
    public Map<String, List<Car>> groupByComponent() {
        return cars
                .stream()
                .flatMap(car -> toComponents.apply(car).stream())
                .distinct()
                .collect(toMap(
                        identity(),
                        component -> cars
                                .stream()
                                .filter(car -> car.hasComponent(component))
                                .collect(toList())
                ));
    }
}

