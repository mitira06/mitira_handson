package com.cognizant.ormlearn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cognizant.ormlearn.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Doc 3, Hands-on 2: HQL with 'fetch join' so department and skillList are loaded
    // in a single query (avoids N+1 selects / LazyInitializationException).
    // DISTINCT is required here: 'left join fetch e.skillList' is a to-many join, so the SQL
    // returns one row per skill. Without DISTINCT, an employee with 2 skills comes back as
    // 2 duplicate Employee objects in the List (classic JPA/Hibernate Cartesian product issue).
    // NOTE: doc uses 'e.permanent = 1' (works on MySQL). H2 is stricter about types and
    // requires comparing a BOOLEAN column to 'true', not the integer 1.
    @Query(value = "SELECT DISTINCT e FROM Employee e left join fetch e.department d left join fetch e.skillList WHERE e.permanent = true")
    List<Employee> getAllPermanentEmployees();

    // Doc 3, Hands-on 4: average salary across all employees
    @Query(value = "SELECT AVG(e.salary) FROM Employee e")
    double getAverageSalary();

    // Doc 3, Hands-on 4: average salary filtered by department id
    @Query(value = "SELECT AVG(e.salary) FROM Employee e where e.department.id = :id")
    double getAverageSalary(@Param("id") int id);

    // Doc 3, Hands-on 5: Native Query example (raw SQL instead of HQL)
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();
}
