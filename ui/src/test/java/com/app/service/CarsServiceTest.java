package com.app.service;

import com.app.domain.car.Car;
import com.app.domain.car.type.Color;
import com.app.extension.CarsServiceExtension;
import com.app.service.exception.CarsServiceException;
import com.app.service.type.SortItem;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(CarsServiceExtension.class)
@RequiredArgsConstructor
public class CarsServiceTest {

    private final CarsService carsService;

    @Test
    @DisplayName("when initialization is succesfull and list of cars is loaded")
    public void sortTest1() {
        assertThat(carsService.getCars())
                .isNotEmpty()
                .hasSize(8);
    }

    @Test
    @DisplayName("when sortItem is null")
    public void sortTest2() {

        assertThatThrownBy(() -> carsService.sort(null, true))
                .isInstanceOf(CarsServiceException.class)
                .hasMessageStartingWith("Sort item ");

    }




    @Test
    @DisplayName("when elements are sorted by color")
    public void sortTest3() {

        var cars = carsService.sort(SortItem.COLOR, false);

        var expected = Car.builder()
                .model("BMW")
                .price(new BigDecimal("290"))
                .color(Color.BLACK)
                .mileage(1500)
                .components(List.of("ABS", "ALLOY WHEELS","SUBWOOFER"))
                .build();

        var firstElement = cars.get(0);
        var lastElement = cars.get(cars.size() - 1);

        assertThat(firstElement.hasColor(Color.BLACK))
                .isTrue();
        assertThat(lastElement.hasColor(Color.WHITE))
                .isTrue();


    }


    @Test
    @DisplayName("when elements are sorted by mileage")
    public void sortTest4() {

        var cars = carsService.sort(SortItem.MILEAGE, true);


        var expectedFistCar = Car.builder()
                .model("RENAULT")
                .price(new BigDecimal("100"))
                .color(Color.RED)
                .mileage(3000)
                .components(List.of("AIR CONDITIONING", "LEATHER", "TEMPOMAT"))
                .build();

        var expectedLastCar = Car.builder()
                .model("FERRARI")
                .price(new BigDecimal("250"))
                .color(Color.RED)
                .mileage(500)
                .components(List.of("LEATHER", "AIR CONDITIONING", "TEMPOMAT", "SPOILER"))
                .build();

       assertThat(cars)
               .isNotEmpty()
               .hasSize(8)
               .startsWith(expectedFistCar)
               .endsWith(expectedLastCar);

    }

    @Test
    @DisplayName("when elements are sorted by model")
    public void sortTest5() {

        var cars = carsService.sort(SortItem.MODEL, false);

        var expectedFirstCar = cars.get(0);
        var expectedLastCar = cars.get(cars.size() - 1);


        assertThat(cars)
                .hasSize(8)
                .isNotEmpty()
                .startsWith(expectedFirstCar)
                .endsWith(expectedLastCar);

    }

    @Test
    @DisplayName("when elements are sorted by price")
    public void sortTest6() {

        var cars = carsService.sort(SortItem.PRICE, true);


        var expectedFirstCar = Car.builder()
                .model("BMW")
                .price(new BigDecimal("290"))
                .color(Color.BLACK)
                .mileage(1500)
                .components(List.of("ABS", "ALLOY WHEELS","SUBWOOFER"))
                .build();


        var expectedLastCar = Car.builder()
                .model("ROVER")
                .price(new BigDecimal("90"))
                .color(Color.GREEN)
                .mileage(1200)
                .components(List.of("ALLOY WHEELS", "SPOILER","SUBWOOFER"))
                .build();


        assertThat(cars)
                .isNotEmpty()
                .hasSize(8)
                .startsWith(expectedFirstCar)
                .endsWith(expectedLastCar);
    }

    @Test
    @DisplayName("when mileage is lower than 0")
    public void findCarsWithMileageGreaterThanTest1() {

        assertThatThrownBy(() -> carsService.findCarsWithMileageGreaterThan(-5))
                .isInstanceOf(CarsServiceException.class)
                .hasMessage("Mileage is out of range");

    }

    @Test
    @DisplayName("when input mileage is 2400")
    public void findCarsWithMileageGreaterThanTest2() {

        var expected = Car.builder()
                .model("RENAULT")
                .price(new BigDecimal("100"))
                .color(Color.RED)
                .mileage(3000)
                .components(List.of("AIR CONDITIONING", "LEATHER", "TEMPOMAT"))
                .build();

        assertThat(carsService.findCarsWithMileageGreaterThan(2400))
                .isNotEmpty()
                .hasSize(2)
                .contains(expected);

    }

    @Test
    @DisplayName("when price from is null")
    public void findCarsWithPriceInRangeTest1() {

    assertThatThrownBy(() -> carsService.findCarsWithPriceInRange(null,new BigDecimal("100")))
            .isInstanceOf(CarsServiceException.class)
            .hasMessage("Price from is null");

    }

    @Test
    @DisplayName("when price to is null")
    public void findCarsWithPriceInRangeTest2() {

    assertThatThrownBy(() -> carsService.findCarsWithPriceInRange(new BigDecimal("100"),null))
            .isInstanceOf(CarsServiceException.class)
            .hasMessage("Price to is null");

    }

    @Test
    @DisplayName("when price from is greater than price to")
    public void findCarsWithPriceInRangeTest3() {

    assertThatThrownBy(() -> carsService.findCarsWithPriceInRange(new BigDecimal("500"),new BigDecimal("200")))
            .isInstanceOf(CarsServiceException.class)
            .hasMessage("Incorrect price range");

    }

    @Test
    @DisplayName("when price is in range from 200 to 300")
    public void findCarsWithPriceInRangeTest4() {

        var expected = Car.builder()
                .model("BMW")
                .price(new BigDecimal("290"))
                .color(Color.BLACK)
                .mileage(1500)
                .components(List.of("ABS", "ALLOY WHEELS","SUBWOOFER"))
                .build();

        assertThat(carsService.findCarsWithPriceInRange(new BigDecimal("200"), new BigDecimal("300")))
                .isNotEmpty()
                .hasSize(3)
                .contains(expected);

    }

    @Test
    @DisplayName("when cars components are sorted")
    public void carsWithSortedComponentsTest() {

        var carsWithSortedComponents = carsService.carsWithSortedComponents();

        var expected1 = Car.builder()
                .model("SKODA")
                .price(new BigDecimal("130"))
                .color(Color.WHITE)
                .mileage(1700)
                .components(List.of("ABS", "BLUETOOTH", "SPOILER", "TEMPOMAT"))
                .build();

        var expected2 = Car.builder()
                .model("FORD")
                .price(new BigDecimal("150"))
                .color(Color.GREEN)
                .mileage(1600)
                .components(List.of("ALLOY WHEELS", "AUTOMATIC GEAR", "TEMPOMAT"))
                .build();

        assertThat(carsWithSortedComponents)
                .isNotEmpty()
                .hasSize(8)
                .contains(expected1,expected2);

    }

    @Test
    @DisplayName("when cars are grouped by component")
    public void groupByComponentTest() {

        var mapWithCarsGroupedByComponents = carsService.groupByComponent();

        var car1 = Car.builder()
                .model("BMW")
                .price(new BigDecimal("290"))
                .color(Color.BLACK)
                .mileage(1500)
                .components(List.of("ABS", "ALLOY WHEELS","SUBWOOFER"))
                .build();


        var car2 = Car.builder()
                .model("ROVER")
                .price(new BigDecimal("90"))
                .color(Color.GREEN)
                .mileage(1200)
                .components(List.of("ALLOY WHEELS", "SPOILER","SUBWOOFER"))
                .build();

        var listWithCarsHavingSubwoofer = List.of(car1,car2);

        assertThat(mapWithCarsGroupedByComponents)
                .isNotEmpty()
                .hasSize(9)
                .containsKeys("LEATHER","AUTOMATIC GEAR","AIR CONDITIONING","SPOILER")
                .contains(entry("SUBWOOFER",listWithCarsHavingSubwoofer));

    }

}
