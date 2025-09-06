package com.elearningplatform.data.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Rating {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int value;
    Rating(int value) {
        this.value = value;
    }
    public static Rating fromValue(int value) {
        return Arrays.stream(values())
                .filter(rating -> rating.getValue() == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid rating value: " + value));
    }
}
