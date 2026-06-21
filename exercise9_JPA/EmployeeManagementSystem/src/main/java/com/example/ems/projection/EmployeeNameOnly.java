package com.example.ems.projection;

import org.springframework.beans.factory.annotation.Value;

/**
 * Exercise 8: Interface-based projection. Spring Data JPA generates a proxy
 * at runtime that only selects the backing properties it needs (id, name,
 * email), and @Value lets you derive a computed property from them.
 */
public interface EmployeeNameOnly {

    Long getId();

    String getName();

    @Value("#{target.name + ' <' + target.email + '>'}")
    String getDisplayLabel();
}
