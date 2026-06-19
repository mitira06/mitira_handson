package com.example.app.basics;

// Exercise Sheet 7 (JUnit Basic) - Exercise 2 & 4: a simple class with methods to test
public class Calculator {

    private int value;

    public Calculator() {
        this.value = 0;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    // stateful methods, useful to demonstrate setup/teardown (@BeforeEach/@AfterEach)
    public void reset() {
        this.value = 0;
    }

    public void increment() {
        this.value++;
    }

    public int getValue() {
        return value;
    }
}
