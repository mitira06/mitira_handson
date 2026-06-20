package com.example.ems.projection;

import lombok.Getter;

/**
 * Exercise 8: Class-based projection. Populated via a JPQL constructor
 * expression (see EmployeeRepository#fetchEmployeeSummaries), so only the
 * three selected columns are pulled from the database - useful when you
 * don't want/need a full Employee + Department fetch.
 */
@Getter
public class EmployeeSummary {

    private final Long id;
    private final String name;
    private final String departmentName;

    public EmployeeSummary(Long id, String name, String departmentName) {
        this.id = id;
        this.name = name;
        this.departmentName = departmentName;
    }
}
