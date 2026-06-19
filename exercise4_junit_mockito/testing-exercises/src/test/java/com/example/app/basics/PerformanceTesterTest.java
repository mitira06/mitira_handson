package com.example.app.basics;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;

// JUnit Advanced Exercise 5: Timeout and Performance Testing
class PerformanceTesterTest {

    @Test
    void testPerformTask_completesWithinTimeLimit() {
        PerformanceTester tester = new PerformanceTester();

        assertTimeout(Duration.ofMillis(500), () -> tester.performTask());
    }
}
