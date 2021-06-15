package com.app.domain.car;

import com.app.domain.car.type.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CarTest {

    @Test
    @DisplayName("when mileage is greater than given number")
    public void test1() {

        var car = Car.builder()
                .mileage(1000)
                .build();

        assertThat(car.hasMileageGreaterThan(900))
                .isTrue();

    }

    @Test
    @DisplayName("when mileage is smaller than given number")
    public void test2() {

        var car = Car.builder()
                .mileage(1000)
                .build();

        assertThat(car.hasMileageGreaterThan(1100))
                .isFalse();

    }

    @Test
    @DisplayName("when car has given color")
    public void test3() {

        var car = Car.builder()
                .color(Color.BLACK)
                .build();

        assertThat(car.hasColor(Color.BLACK))
                .isTrue();

    }

    @Test
    @DisplayName("when car hasn't given color")
    public void test4() {

        var car = Car.builder()
                .color(Color.BLACK)
                .build();

        assertThat(car.hasColor(Color.GREEN))
                .isFalse();

    }

    @Test
    @DisplayName("when car has price in given range")
    public void test5() {

        var car = Car.builder()
                .price(new BigDecimal("500"))
                .build();

        var priceFrom = new BigDecimal("100");
        var priceTo = new BigDecimal("1000");

        assertThat(car.hasPriceInRange(priceFrom,priceTo))
                .isTrue();

    }

    @Test
    @DisplayName("when car hasn't price in given range")
    public void test6() {

        var car = Car.builder()
                .price(new BigDecimal("50"))
                .build();

        var priceFrom = new BigDecimal("100");
        var priceTo = new BigDecimal("1000");

        assertThat(car.hasPriceInRange(priceFrom,priceTo))
                .isFalse();

    }

    @Test
    @DisplayName("when components are sorted")
    public void test7() {

        var car = Car.builder()
                .components(List.of("TEMPOMAT","ABS","LEATHER"))
                .build();

        var expectedCar = Car.builder()
                .components(List.of("ABS","LEATHER","TEMPOMAT"))
                .build();

        assertThat(car.withSortedComponents())
                .isEqualTo(expectedCar);

    }

    @Test
    @DisplayName("when car has given component")
    public void test8() {

        var car = Car.builder()
                .components(List.of("ABS","LEATHER","TEMPOMAT"))
                .build();

        assertThat(car.hasComponent("ABS"))
                .isTrue();

    }

    @Test
    @DisplayName("when car hasn't given component")
    public void test9() {

        var car = Car.builder()
                .components(List.of("ABS","LEATHER","TEMPOMAT"))
                .build();

        assertThat(car.hasComponent("AIRBAG"))
                .isFalse();

    }


}
