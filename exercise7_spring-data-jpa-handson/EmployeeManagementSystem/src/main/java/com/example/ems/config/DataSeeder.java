package com.example.ems.config;

import com.example.ems.model.Department;
import com.example.ems.model.Employee;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Convenience seeder so the REST endpoints have data to query immediately
 * after startup (not part of the exercises themselves, just a test fixture).
 */
@Component
@RequiredArgsConstructor
public class DataSeeder implements ApplicationRunner {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (departmentRepository.count() > 0) {
            return;
        }

        Department engineering = departmentRepository.save(Department.builder().name("Engineering").build());
        Department sales = departmentRepository.save(Department.builder().name("Sales").build());
        Department hr = departmentRepository.save(Department.builder().name("Human Resources").build());

        employeeRepository.save(Employee.builder().name("Asha Rao").email("asha.rao@acme.com").department(engineering).build());
        employeeRepository.save(Employee.builder().name("Ben Carter").email("ben.carter@acme.com").department(engineering).build());
        employeeRepository.save(Employee.builder().name("Carlos Diaz").email("carlos.diaz@acme.com").department(sales).build());
        employeeRepository.save(Employee.builder().name("Divya Menon").email("divya.menon@acme.com").department(sales).build());
        employeeRepository.save(Employee.builder().name("Evan Wright").email("evan.wright@acme.com").department(hr).build());
    }
}
