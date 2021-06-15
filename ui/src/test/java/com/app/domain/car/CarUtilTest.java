package com.app.domain.car;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CarUtilTest {

    @Test
    @DisplayName("when car have 3 components")
    public void test1() {

        var car = Car.builder()
                .components(List.of("ABS","LEATHER","TEMPOMAT"))
                .build();

        var expectedList = List.of("ABS","LEATHER","TEMPOMAT");

        Assertions.assertThat(CarUtil.toComponents.apply(car))
                .isNotEmpty()
                .hasSize(3)
                .isEqualTo(expectedList);


    }

}
