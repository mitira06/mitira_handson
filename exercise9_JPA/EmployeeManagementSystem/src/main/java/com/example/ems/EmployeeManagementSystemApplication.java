package com.example.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Exercise 1: Spring Boot project setup.
 *
 * NOTE: The default single-DataSource auto-configuration classes are excluded
 * because Exercise 9 requires managing multiple data sources. The primary and
 * secondary DataSource/EntityManagerFactory/TransactionManager beans are
 * defined explicitly in {@link com.example.ems.config.PrimaryDataSourceConfig}
 * and {@link com.example.ems.config.SecondaryDataSourceConfig}.
 */
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class
})
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl") // Exercise 7: entity auditing
public class EmployeeManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }
}
