package com.example.webstore.utils;

public class Validator {
    // 양수 검사를 수행하는 메서드
    public static void isPositiveNumber(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("must insert the positive integer");
        }
    }
}
