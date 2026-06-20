package com.example.ems.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Exercise 2: JPA entity - Employee, many-to-one with Department.
 * Exercise 5: Named queries declared here are looked up by Spring Data JPA
 * automatically when a repository method name matches "EntityName.methodName"
 * (see EmployeeRepository#findByEmailDomain).
 * Exercise 10: @DynamicInsert/@DynamicUpdate are Hibernate-specific annotations.
 * The SEQUENCE generator (instead of IDENTITY) with allocationSize=20 is what
 * allows Hibernate to actually batch INSERT statements (see Exercise 10 /
 * EmployeeService#bulkInsertEmployees).
 */
@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
@DynamicUpdate
@NamedQueries({
        @NamedQuery(
                name = "Employee.findByEmailDomain",
                query = "SELECT e FROM Employee e WHERE e.email LIKE CONCAT('%@', :domain)"
        ),
        @NamedQuery(
                name = "Employee.countByDepartment",
                query = "SELECT COUNT(e) FROM Employee e WHERE e.department.id = :departmentId"
        )
})
public class Employee extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_seq")
    @SequenceGenerator(name = "emp_seq", sequenceName = "emp_sequence", allocationSize = 20)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;
}
