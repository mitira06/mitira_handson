package com.example.app.calculator;

import org.springframework.stereotype.Service;

// Exercise Sheet 3 - Exercise 1: Basic Unit Test for a Service Method
@Service
public class CalculatorService {
    public int add(int a, int b) {
        return a + b;
    }
}
