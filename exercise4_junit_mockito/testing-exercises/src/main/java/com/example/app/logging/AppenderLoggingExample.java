package com.example.app.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// SLF4J Exercise 3: Using Different Appenders
// Pairs with src/main/resources/logback.xml which defines console + file appenders
public class AppenderLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(AppenderLoggingExample.class);

    public static void main(String[] args) {
        logger.debug("Debug message - visible in console and app.log");
        logger.info("Info message - visible in console and app.log");
        logger.warn("Warning message - visible in console and app.log");
        logger.error("Error message - visible in console and app.log");
    }
}
