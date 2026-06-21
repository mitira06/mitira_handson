package com.example.ems.service;

import com.example.ems.dto.DepartmentRequest;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.model.Department;
import com.example.ems.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Exercise 4: Basic CRUD operations for Department.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department createDepartment(DepartmentRequest request) {
        Department department = Department.builder().name(request.getName()).build();
        return departmentRepository.save(department);
    }

    @Transactional(readOnly = true)
    public Department getDepartment(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department updateDepartment(Long id, DepartmentRequest request) {
        Department department = getDepartment(id);
        department.setName(request.getName());
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        Department department = getDepartment(id);
        departmentRepository.delete(department);
    }
}
