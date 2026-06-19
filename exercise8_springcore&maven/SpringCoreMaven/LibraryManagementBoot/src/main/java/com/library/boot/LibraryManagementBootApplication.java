package com.library.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication bundles three annotations:
// @Configuration, @EnableAutoConfiguration, and @ComponentScan -- this single
// annotation is why Spring Boot needs so much less manual setup than classic Spring.
@SpringBootApplication
public class LibraryManagementBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementBootApplication.class, args);
    }
}
