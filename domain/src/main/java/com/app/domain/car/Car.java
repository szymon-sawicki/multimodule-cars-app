package com.app.domain.car;

import com.app.domain.car.type.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode

/**
 * Main domain class. Contains fields representing car properties and some methods used to return modified object.
 * @author Szymon Sawicki
 */

public class Car {
    String model;
    BigDecimal price;
    Color color;
    int mileage;
    List<String> components;

    /**
     * @param mileage limit of results
     * @return boolean value if the object's mileage is greater than given limit
     */

    public boolean hasMileageGreaterThan(int mileage) {
        return this.mileage > mileage;
    }

    /**
     * @param colorToCheck
     * @return boolean value if the auto has given color
     */

    public boolean hasColor(Color colorToCheck) {
        return colorToCheck.equals(color);
    }

    /**
     * @param priceFrom lower range of price
     * @param priceTo   higher range of price
     * @return boolean value if car has price in given range
     */

    public boolean hasPriceInRange(BigDecimal priceFrom, BigDecimal priceTo) {
        return price.compareTo(priceFrom) >= 0 && price.compareTo(priceTo) <= 0;
    }

    /**
     * @param component component to check
     * @return boolean value if car contains given component
     */

    public boolean hasComponent(String component) {
        return components.contains(component);
    }

    /**
     * @return new car object with sorted listof components
     */

    public Car withSortedComponents() {
        return Car
                .builder()
                .model(model)
                .price(price)
                .color(color)
                .mileage(mileage)
                .components(components.stream().sorted().collect(Collectors.toList()))
                .build();
    }

    @Override
    public String toString() {
        var message = "CAR:\n";
        message += "MODEL:      " + model + "\n";
        message += "PRICE:      " + price + "\n";
        message += "COLOR:      " + color + "\n";
        message += "MILEAGE:    " + mileage + "\n";
        message += "COMPONENTS: " + String.join(", ", components) + "\n";
        return message;
    }
}
