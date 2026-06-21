package com.example.ems.controller;

import com.example.ems.dto.EmployeeRequest;
import com.example.ems.model.Employee;
import com.example.ems.projection.EmployeeNameOnly;
import com.example.ems.projection.EmployeeSummary;
import com.example.ems.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // ---------------- Exercise 4: CRUD ----------------

    @PostMapping
    public ResponseEntity<Employee> create(@Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    // ---------------- Exercise 5: custom query methods ----------------

    @GetMapping("/by-department-name")
    public ResponseEntity<List<Employee>> byDepartmentName(@RequestParam String name) {
        return ResponseEntity.ok(employeeService.searchByDepartmentName(name));
    }

    @GetMapping("/by-email-domain")
    public ResponseEntity<List<Employee>> byEmailDomain(@RequestParam String domain) {
        return ResponseEntity.ok(employeeService.findByEmailDomain(domain));
    }

    // ---------------- Exercise 6: pagination + sorting ----------------

    @GetMapping("/search")
    public ResponseEntity<Page<Employee>> search(
            @RequestParam(required = false) String departmentName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction) {
        return ResponseEntity.ok(employeeService.searchEmployees(departmentName, page, size, sortBy, direction));
    }

    // ---------------- Exercise 8: projections ----------------

    @GetMapping("/projections/name-only")
    public ResponseEntity<List<EmployeeNameOnly>> nameOnly(@RequestParam Long departmentId) {
        return ResponseEntity.ok(employeeService.getEmployeeNameOnlyByDepartment(departmentId));
    }

    @GetMapping("/projections/summary")
    public ResponseEntity<List<EmployeeSummary>> summaries() {
        return ResponseEntity.ok(employeeService.getEmployeeSummaries());
    }

    // ---------------- Exercise 10: Hibernate batch insert ----------------

    @PostMapping("/bulk")
    public ResponseEntity<String> bulkInsert(@Valid @RequestBody List<EmployeeRequest> requests) {
        employeeService.bulkInsertEmployees(requests);
        return ResponseEntity.status(HttpStatus.CREATED).body(requests.size() + " employees inserted in batch.");
    }
}
