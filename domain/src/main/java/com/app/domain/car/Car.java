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
public class Car {
    String model;
    BigDecimal price;
    Color color;
    int mileage;
    List<String> components;


    public boolean hasMileageGreaterThan(int mileage) {
        return this.mileage > mileage;
    }

    public boolean hasColor(Color colorToCheck) {
        return colorToCheck.equals(color);
    }

    public boolean hasPriceInRange(BigDecimal priceFrom, BigDecimal priceTo) {
        return price.compareTo(priceFrom) >= 0 && price.compareTo(priceTo) <= 0;
    }


    public boolean hasComponent(String component) {
        return components.contains(component);
    }

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
