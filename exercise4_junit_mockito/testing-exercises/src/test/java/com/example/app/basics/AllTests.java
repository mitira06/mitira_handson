package com.example.app.basics;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

// JUnit Advanced Exercise 2: Test Suites and Categories
// Run this single class in your IDE/Maven to execute all the listed test classes together.
@Suite
@SelectClasses({
        CalculatorBasicTest.class,
        CalculatorAaaTest.class,
        AssertionsTest.class,
        EvenCheckerTest.class,
        ExceptionThrowerTest.class
})
public class AllTests {
}
