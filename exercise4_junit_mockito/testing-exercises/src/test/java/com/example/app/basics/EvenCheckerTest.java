package com.example.app.basics;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// JUnit Advanced Exercise 1: Parameterized Tests
class EvenCheckerTest {

    private final EvenChecker evenChecker = new EvenChecker();

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 100, 0})
    void testIsEven_withEvenNumbers(int number) {
        assertTrue(evenChecker.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 7, 99, -3})
    void testIsEven_withOddNumbers(int number) {
        assertFalse(evenChecker.isEven(number));
    }
}
