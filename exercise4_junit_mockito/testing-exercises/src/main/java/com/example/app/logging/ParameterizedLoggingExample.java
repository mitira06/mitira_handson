package com.example.app.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// SLF4J Exercise 2: Parameterized Logging
public class ParameterizedLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        String username = "alice";
        int loginAttempt = 3;

        // single placeholder
        logger.info("User {} logged in", username);

        // multiple placeholders
        logger.warn("User {} failed login attempt number {}", username, loginAttempt);

        // exception logging with parameterized message
        try {
            throw new RuntimeException("Simulated failure");
        } catch (RuntimeException e) {
            logger.error("Unexpected error while processing user {}", username, e);
        }
    }
}
