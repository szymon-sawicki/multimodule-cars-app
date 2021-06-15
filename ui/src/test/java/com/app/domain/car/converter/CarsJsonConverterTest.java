package com.app.domain.car.converter;

import com.app.extension.CarsJsonConverterExtension;
import com.app.domain.car.Car;
import com.app.domain.car.type.Color;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(CarsJsonConverterExtension.class)
@RequiredArgsConstructor

public class CarsJsonConverterTest {

    private final CarsJsonConverter carsJsonConverter;

    @Test
    @DisplayName("when json converter works correctly")
    public void test1() {

        var expectedCars = List.of(
                Car
                        .builder()
                        .model("BMW")
                        .price(new BigDecimal("225"))
                        .mileage(1600)
                        .color(Color.WHITE)
                        .components(List.of("LEATHER",
                                "AUTOMATIC GEAR",
                                "BLUETOOTH",
                                "ABS"))
                        .build()
        );

        var carsFromJson = carsJsonConverter.fromJson().orElseThrow();

        Assertions.assertDoesNotThrow(() -> assertThat(carsFromJson)
                .hasSize(1)
                .containsAnyElementsOf(expectedCars)
        );

    }

}
