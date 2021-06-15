package com.app.domain.configs.validator;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * interface used to validate objects
 * @param <T> type of object to validate
 */

public interface Validator<T> {

    /**
     *
     * @param t element to be validate
     * @return map which has key ( name of the field ) a nd value ( validation error message for this field )
     */
    Map<String, String> validate(T t);

    static <T> void validate(Validator<T> validator, T t) {
        var errors = validator.validate(t);
        if (!errors.isEmpty()) {
            var message = errors
                    .entrySet()
                    .stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining(", "));
            throw new ValidatorException("[VALIDATION ERROR]: " + message);
        }
    }
}
