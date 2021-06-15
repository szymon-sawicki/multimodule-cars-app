package com.app.domain.car;

import com.app.domain.car.type.Color;
import com.app.domain.configs.validator.Validator;
import com.app.domain.configs.validator.ValidatorException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CarValidatorTest {


    @Test
    @DisplayName("when car is null")
    public void CarValidatorTest1() {

        Car car = null;
        //var carValidator = new CarValidator().validate(car);

        assertThatThrownBy(() -> Validator.validate(new CarValidator(),car))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("object is null");

    }

    @Test
    @DisplayName("when model contains lowercases")
    public void CarValidatorTest2() {

        Car car = Car.builder()
                .model("mazda")
                .price(new BigDecimal("100"))
                .color(Color.BLACK)
                .mileage(1000)
                .components(List.of("ABS","TEMPOMAT"))
                .build();

        assertThatThrownBy(() -> Validator.validate(new CarValidator(),car))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("should contains only upper case letters");

    }

    @Test
    @DisplayName("when price is zero")
    public void CarValidatorTest3() {

        Car car = Car.builder()
                .model("MAZDA")
                .price(BigDecimal.ZERO)
                .color(Color.BLACK)
                .mileage(1000)
                .components(List.of("ABS","TEMPOMAT"))
                .build();

        assertThatThrownBy(() -> Validator.validate(new CarValidator(),car))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("price should be greater than 0");

    }

    @Test
    @DisplayName("when color is null")
    public void CarValidatorTest4() {

        Car car = Car.builder()
                .model("MAZDA")
                .price(new BigDecimal("200"))
                .color(null)
                .mileage(1000)
                .components(List.of("ABS","TEMPOMAT"))
                .build();

        assertThatThrownBy(() -> Validator.validate(new CarValidator(),car))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("color is null");

    }

    @Test
    @DisplayName("when mileage is negative")
    public void CarValidatorTest5() {

        Car car = Car.builder()
                .model("MAZDA")
                .price(new BigDecimal("200"))
                .color(Color.BLACK)
                .mileage(-1000)
                .components(List.of("ABS","TEMPOMAT"))
                .build();

        assertThatThrownBy(() -> Validator.validate(new CarValidator(),car))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("mileage should be greater than 0");

    }


    @Test
    @DisplayName("when components list is empty")
    public void CarValidatorTest6() {

        Car car = Car.builder()
                .model("MAZDA")
                .price(new BigDecimal("200"))
                .color(Color.BLACK)
                .mileage(1000)
                .components(List.of())
                .build();

        assertThatThrownBy(() -> Validator.validate(new CarValidator(),car))
                .isInstanceOf(ValidatorException.class)
                .hasMessageContaining("components list cannot be empty");

    }

}
