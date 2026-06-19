package com.example.app.basics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// JUnit Advanced Exercise 4: Exception Testing
class ExceptionThrowerTest {

    @Test
    void testThrowException() {
        ExceptionThrower thrower = new ExceptionThrower();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                thrower::throwException
        );

        assertEquals("Something went wrong", exception.getMessage());
    }
}
