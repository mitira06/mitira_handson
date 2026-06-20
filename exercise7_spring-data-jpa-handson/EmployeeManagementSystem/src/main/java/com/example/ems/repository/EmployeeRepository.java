package com.example.ems.repository;

import com.example.ems.model.Employee;
import com.example.ems.projection.EmployeeSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Exercise 3: Repository extending JpaRepository - free CRUD out of the box.
 * Exercise 5: Derived query methods, @Query (JPQL), and a named-query lookup.
 * Exercise 6: Page/Pageable-returning methods support pagination + sorting.
 * Exercise 8: Dynamic + class-based projections.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // ---- Exercise 3/5: derived query methods (Spring Data parses the method name) ----
    List<Employee> findByDepartmentId(Long departmentId);

    List<Employee> findByNameContainingIgnoreCase(String name);

    Optional<Employee> findByEmail(String email);

    long countByDepartmentId(Long departmentId);

    // ---- Exercise 6: pagination + sorting on a derived, nested-property query ----
    Page<Employee> findByDepartment_NameContainingIgnoreCase(String departmentName, Pageable pageable);

    // ---- Exercise 5: custom query using @Query (JPQL) ----
    @Query("SELECT e FROM Employee e WHERE e.department.name = :deptName")
    List<Employee> searchByDepartmentName(@Param("deptName") String deptName);

    // ---- Exercise 5: named query lookup. Spring Data finds the query named
    // "Employee.findByEmailDomain" declared via @NamedQuery on the Employee
    // entity, because this method name matches "EntityName.methodName". ----
    List<Employee> findByEmailDomain(@Param("domain") String domain);

    // ---- Exercise 8: dynamic projection - caller decides the return type ----
    <T> List<T> findByDepartmentId(Long departmentId, Class<T> type);

    // ---- Exercise 8: class-based projection via JPQL constructor expression ----
    @Query("SELECT new com.example.ems.projection.EmployeeSummary(e.id, e.name, e.department.name) FROM Employee e")
    List<EmployeeSummary> fetchEmployeeSummaries();
}
