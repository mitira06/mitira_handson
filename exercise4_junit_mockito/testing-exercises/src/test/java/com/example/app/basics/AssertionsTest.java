package com.example.app.basics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// JUnit Basic Exercise 3: Assertions in JUnit
class AssertionsTest {

    @Test
    void testAssertions() {
        // Assert equals
        assertEquals(5, 2 + 3);

        // Assert true
        assertTrue(5 > 3);

        // Assert false
        assertFalse(5 < 3);

        // Assert null
        assertNull(null);

        // Assert not null
        assertNotNull(new Object());
    }
}
