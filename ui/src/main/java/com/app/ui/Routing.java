package com.app.ui;

import com.app.service.CarsService;
import com.app.service.exception.CarsServiceException;
import com.app.service.type.SortItem;
import com.app.ui.config.JsonTransformer;
import com.app.ui.dto.ResponseDto;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Locale;

import static spark.Spark.*;

@Component
@RequiredArgsConstructor

public class Routing {

    private final CarsService carsService;

    public void routes() {

        path("/cars", () -> {

                    get("",
                            (request, response) -> {
                                response.header("Content-Type", "application/json;charset=utf-8");
                                return carsService.getAllCars();
                            },
                            new JsonTransformer());

                    path("/price", () -> {
                        get("/:from/:to",
                                (request, response) -> {
                                    var priceFrom = request.params(":from");
                                    var priceTo = request.params(":to");
                                    response.header("Content-Type", "application/json;charset=utf-8");
                                    var result = carsService.findCarsWithPriceInRange(new BigDecimal(priceFrom), new BigDecimal(priceTo));
                                    return ResponseDto.builder().data(result);
                                },
                                new JsonTransformer());

                    });

                    path("/mileage/", () -> {

                        get("/:limit",
                                (request, response) -> {
                                    var limit = request.params(":limit");
                                    response.header("Content-Type", "application/json;charset=utf-8");
                                    return ResponseDto.builder().data(carsService.findCarsWithMileageGreaterThan(Integer.parseInt(limit)));
                                },
                                new JsonTransformer());
                    });


                    path("/sort", () -> {
                        get("/:type",
                                (request, response) -> {
                                    var sortType = request.params(":type");
                                    var reversed = request.queryParams("reversed");
                                    response.header("Content-Type", "application/json;charset=utf-8");
                                    var result = carsService.sort(SortItem.valueOf(sortType.toUpperCase(Locale.ROOT)), Boolean.parseBoolean(reversed));
                                    return ResponseDto.builder().data(result);
                                },
                                new JsonTransformer());


                        get("/components",
                                (request, response) -> {
                                    response.header("Content-Type", "application/json;charset=utf-8");
                                    var result = carsService.carsWithSortedComponents();
                                    return ResponseDto.builder().data(result);
                                },
                                new JsonTransformer());
                    });


                    get("/components/",
                            (request, response) -> {
                                response.header("Content-Type", "application/json;charset=utf-8");
                                var result = carsService.groupByComponent();
                                return ResponseDto.builder().data(result);
                            },
                            new JsonTransformer());

                }
        );


        exception(CarsServiceException.class, (exception, request, response) -> {
            response.redirect("/error/" + exception.getMessage(), 301);
        });

        path("/error", () -> {
            get(
                    "/:message",
                    (request, response) -> {
                        var errorMessage = request.params(":message");
                        response.header("Content-Type", "application/json;charset=utf-8");
                        response.status(500);
                        var responseBody = ResponseDto.toError(errorMessage);
                        return toJson(responseBody);
                    },
                    new JsonTransformer()
            );
        });

        internalServerError((request, response) -> {
            response.header("Content-Type", "application/json;charset=utf-8");
            var responseBody = ResponseDto.toError("Unknown internal server exception");
            return toJson(responseBody);
        });

        notFound((request, response) -> {
            response.header("Content-Type", "application/json;charset=utf-8");
            response.status(404);
            var responseBody = ResponseDto.toError("Not found");
            return toJson(responseBody);
        });
    }

    private static <T> String toJson(T data) {
        var gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(data);
    }
}
