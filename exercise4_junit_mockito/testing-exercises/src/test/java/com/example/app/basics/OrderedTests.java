package com.example.app.basics;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// JUnit Advanced Exercise 3: Test Execution Order
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderedTests {

    private static final StringBuilder executionLog = new StringBuilder();

    @Test
    @Order(1)
    void firstTest() {
        executionLog.append("first ");
        System.out.println("Running firstTest");
    }

    @Test
    @Order(2)
    void secondTest() {
        executionLog.append("second ");
        System.out.println("Running secondTest");
    }

    @Test
    @Order(3)
    void thirdTest() {
        executionLog.append("third ");
        System.out.println("Running thirdTest");
        // by the time this runs, the log should reflect the declared order
        org.junit.jupiter.api.Assertions.assertEquals("first second third ", executionLog.toString());
    }
}
