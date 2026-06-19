package com.example.app.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Spring Testing Exercises - Exercise 1: Basic Unit Test for a Service Method
class CalculatorServiceTest {

    @Test
    void testAdd() {
        CalculatorService calculatorService = new CalculatorService();
        int result = calculatorService.add(2, 3);
        assertEquals(5, result);
    }
}
