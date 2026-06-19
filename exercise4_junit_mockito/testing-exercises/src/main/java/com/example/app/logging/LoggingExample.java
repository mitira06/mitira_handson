package com.example.app.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// SLF4J Exercise 1: Logging Error Messages and Warning Levels
public class LoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        logger.error("This is an error message");
        logger.warn("This is a warning message");
    }
}
