package com.example.ems.service;

import com.example.ems.dto.EmployeeRequest;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.model.Department;
import com.example.ems.model.Employee;
import com.example.ems.projection.EmployeeNameOnly;
import com.example.ems.projection.EmployeeSummary;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Exercise 4: Basic CRUD operations for Employee.
 * Exercise 5: Custom query methods (delegated to the repository).
 * Exercise 6: Pagination and sorting.
 * Exercise 8: Projections.
 * Exercise 10: Batch processing with Hibernate (bulkInsertEmployees).
 */
@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final AuditLogService auditLogService;

    // Bound to the "primary" persistence unit defined in PrimaryDataSourceConfig.
    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    // ---------------- Exercise 4: CRUD ----------------

    public Employee createEmployee(EmployeeRequest request) {
        Department department = findDepartmentOrThrow(request.getDepartmentId());
        Employee employee = Employee.builder()
                .name(request.getName())
                .email(request.getEmail())
                .department(department)
                .build();
        Employee saved = employeeRepository.save(employee);
        auditLogService.log("CREATE", "Employee", saved.getId());
        return saved;
    }

    @Transactional(readOnly = true)
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = getEmployee(id);
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        if (!employee.getDepartment().getId().equals(request.getDepartmentId())) {
            employee.setDepartment(findDepartmentOrThrow(request.getDepartmentId()));
        }
        Employee updated = employeeRepository.save(employee);
        auditLogService.log("UPDATE", "Employee", updated.getId());
        return updated;
    }

    public void deleteEmployee(Long id) {
        Employee employee = getEmployee(id);
        employeeRepository.delete(employee);
        auditLogService.log("DELETE", "Employee", id);
    }

    private Department findDepartmentOrThrow(Long departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + departmentId));
    }

    // ---------------- Exercise 5: custom query methods ----------------

    @Transactional(readOnly = true)
    public List<Employee> searchByDepartmentName(String departmentName) {
        return employeeRepository.searchByDepartmentName(departmentName);
    }

    @Transactional(readOnly = true)
    public List<Employee> findByEmailDomain(String domain) {
        return employeeRepository.findByEmailDomain(domain);
    }

    // ---------------- Exercise 6: pagination + sorting ----------------

    @Transactional(readOnly = true)
    public Page<Employee> searchEmployees(String departmentName, int page, int size, String sortBy, String direction) {
        Sort sort = "DESC".equalsIgnoreCase(direction) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        if (departmentName != null && !departmentName.isBlank()) {
            return employeeRepository.findByDepartment_NameContainingIgnoreCase(departmentName, pageable);
        }
        return employeeRepository.findAll(pageable);
    }

    // ---------------- Exercise 8: projections ----------------

    @Transactional(readOnly = true)
    public List<EmployeeNameOnly> getEmployeeNameOnlyByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId, EmployeeNameOnly.class);
    }

    @Transactional(readOnly = true)
    public List<EmployeeSummary> getEmployeeSummaries() {
        return employeeRepository.fetchEmployeeSummaries();
    }

    // ---------------- Exercise 10: Hibernate batch processing ----------------

    /**
     * Inserts many employees using the EntityManager directly so we control
     * exactly when flush()/clear() happen, which is what makes Hibernate's
     * JDBC batching (hibernate.jdbc.batch_size, see application.properties)
     * actually kick in. Relies on the SEQUENCE id generator with a matching
     * allocationSize on Employee - IDENTITY generators defeat batching
     * because Hibernate must round-trip to the DB for every single insert.
     */
    public void bulkInsertEmployees(List<EmployeeRequest> requests) {
        final int batchSize = 20;
        for (int i = 0; i < requests.size(); i++) {
            EmployeeRequest req = requests.get(i);
            Department department = findDepartmentOrThrow(req.getDepartmentId());
            Employee employee = Employee.builder()
                    .name(req.getName())
                    .email(req.getEmail())
                    .department(department)
                    .build();
            entityManager.persist(employee);

            if (i > 0 && i % batchSize == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
        entityManager.flush();
        entityManager.clear();
    }
}
