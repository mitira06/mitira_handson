package com.example.app.basics;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// JUnit Basic Exercise 4: Arrange-Act-Assert (AAA) Pattern, Test Fixtures, Setup/Teardown
// Note: JUnit 5 uses @BeforeEach/@AfterEach (JUnit 4 used @Before/@After)
class CalculatorAaaTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }

    @Test
    void testIncrementUsingAaaPattern() {
        // Arrange
        calculator.reset();

        // Act
        calculator.increment();
        calculator.increment();

        // Assert
        assertEquals(2, calculator.getValue());
    }

    @Test
    void testAddUsingAaaPattern() {
        // Arrange
        int a = 4;
        int b = 6;

        // Act
        int result = calculator.add(a, b);

        // Assert
        assertEquals(10, result);
    }
}
