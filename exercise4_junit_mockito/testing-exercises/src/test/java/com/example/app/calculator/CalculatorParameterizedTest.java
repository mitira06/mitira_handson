package com.example.app.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Spring Testing Exercise 9: Parameterized Test with JUnit
class CalculatorParameterizedTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "-1, 1, 0",
            "0, 0, 0",
            "100, 200, 300"
    })
    void testAdd_withMultipleInputs(int a, int b, int expectedSum) {
        assertEquals(expectedSum, calculatorService.add(a, b));
    }
}
