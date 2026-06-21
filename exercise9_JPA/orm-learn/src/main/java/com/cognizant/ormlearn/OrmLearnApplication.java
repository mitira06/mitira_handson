package com.cognizant.ormlearn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    private static CountryService countryService;
    private static EmployeeService employeeService;
    private static DepartmentService departmentService;
    private static SkillService skillService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main");

        countryService = context.getBean(CountryService.class);
        employeeService = context.getBean(EmployeeService.class);
        departmentService = context.getBean(DepartmentService.class);
        skillService = context.getBean(SkillService.class);

        // ===================== Doc 1 =====================
        testGetAllCountries();

        // ===================== Doc 2, Hands-on 1 =====================
        testSearchCountriesByName();
        testSearchCountriesByNameSorted();
        testFindCountriesStartingWith();

        // ===================== Doc 1, Hands-on 6-9: Country CRUD =====================
        testFindCountryByCode();
        testAddCountry();
        testUpdateCountry();
        testDeleteCountry();

        // ===================== Doc 2, Hands-on 4: @ManyToOne (Employee -> Department) =====================
        testGetEmployee();
        testAddEmployee();
        testUpdateEmployee();

        // ===================== Doc 2, Hands-on 5: @OneToMany (Department -> Employees) =====================
        testGetDepartment();

        // ===================== Doc 2, Hands-on 6: @ManyToMany (Employee <-> Skill) =====================
        testAddSkillToEmployee();

        // ===================== Doc 3, Hands-on 2: HQL with fetch join =====================
        testGetAllPermanentEmployees();

        // ===================== Doc 3, Hands-on 4: HQL aggregate function (AVG) =====================
        testGetAverageSalary();
        testGetAverageSalaryByDepartment();

        // ===================== Doc 3, Hands-on 5: Native Query =====================
        testGetAllEmployeesNative();
    }

    // ----------------------------------------------------------------------
    // Doc 1 / Doc 2 Hands-on 1: Country
    // ----------------------------------------------------------------------

    private static void testGetAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries={}", countries);
        LOGGER.info("End");
    }

    // Search box: countries whose name contains "ou" (e.g. Bouvet Island, Djibouti, Guadeloupe...)
    private static void testSearchCountriesByName() {
        LOGGER.info("Start");
        List<Country> countries = countryService.searchCountriesByName("ou");
        LOGGER.debug("countries containing 'ou'={}", countries);
        LOGGER.info("End");
    }

    // Same search, but results sorted alphabetically by name
    private static void testSearchCountriesByNameSorted() {
        LOGGER.info("Start");
        List<Country> countries = countryService.searchCountriesByNameSorted("ou");
        LOGGER.debug("countries containing 'ou' (sorted)={}", countries);
        LOGGER.info("End");
    }

    // Alphabet index: countries starting with "Z" (e.g. Zambia, Zimbabwe)
    private static void testFindCountriesStartingWith() {
        LOGGER.info("Start");
        List<Country> countries = countryService.findCountriesStartingWith("Z");
        LOGGER.debug("countries starting with 'Z'={}", countries);
        LOGGER.info("End");
    }

    // ----------------------------------------------------------------------
    // Doc 1, Hands-on 6-9: Country CRUD
    // ----------------------------------------------------------------------

    // Hands-on 6: find a country based on country code
    private static void testFindCountryByCode() {
        LOGGER.info("Start");
        try {
            Country country = countryService.findCountryByCode("IN");
            LOGGER.debug("Country:{}", country);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Country not found", e);
        }
        LOGGER.info("End");
    }

    // Hands-on 7: add a new country
    private static void testAddCountry() {
        LOGGER.info("Start");
        Country country = new Country();
        country.setCode("ZZ");
        country.setName("Test Country");
        countryService.addCountry(country);
        try {
            Country added = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Added Country:{}", added);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Country not found", e);
        }
        LOGGER.info("End");
    }

    // Hands-on 8: update a country based on code (uses the country added in Hands-on 7)
    private static void testUpdateCountry() {
        LOGGER.info("Start");
        try {
            countryService.updateCountry("ZZ", "Test Country Updated");
            Country updated = countryService.findCountryByCode("ZZ");
            LOGGER.debug("Updated Country:{}", updated);
        } catch (CountryNotFoundException e) {
            LOGGER.error("Country not found", e);
        }
        LOGGER.info("End");
    }

    // Hands-on 9: delete a country based on code (uses the country added in Hands-on 7)
    private static void testDeleteCountry() {
        LOGGER.info("Start");
        countryService.deleteCountry("ZZ");
        try {
            countryService.findCountryByCode("ZZ");
            LOGGER.debug("Country still present after delete - unexpected");
        } catch (CountryNotFoundException e) {
            LOGGER.debug("Country 'ZZ' successfully deleted, as expected: {}", e.getMessage());
        }
        LOGGER.info("End");
    }

    // ----------------------------------------------------------------------
    // Doc 2, Hands-on 4: @ManyToOne mapping between Employee and Department
    // ----------------------------------------------------------------------

    // Gets employee id 1 along with its department (EAGER fetch by default for @ManyToOne)
    private static void testGetEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee:{}", employee);
        LOGGER.debug("Department:{}", employee.getDepartment());
        LOGGER.debug("Skills:{}", employee.getSkillList());
        LOGGER.info("End");
    }

    // Creates a new employee in department 1 and verifies it was added
    private static void testAddEmployee() {
        LOGGER.info("Start");
        Employee employee = new Employee();
        employee.setName("Test Employee Add");
        employee.setSalary(50000.00);
        employee.setPermanent(true);

        Department department = departmentService.get(1);
        employee.setDepartment(department);

        employeeService.save(employee);
        LOGGER.debug("Added Employee:{}", employee);
        LOGGER.info("End");
    }

    // Moves employee id 1 to a different department (department id 2) and saves
    private static void testUpdateEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);

        Department department = departmentService.get(2);
        employee.setDepartment(department);

        employeeService.save(employee);
        LOGGER.debug("Updated Employee:{}", employee);
        LOGGER.info("End");
    }

    // ----------------------------------------------------------------------
    // Doc 2, Hands-on 5: @OneToMany mapping between Department and Employee
    // ----------------------------------------------------------------------

    // Department id 3 has more than one employee (Divya, Suresh, Lakshmi)
    private static void testGetDepartment() {
        LOGGER.info("Start");
        Department department = departmentService.get(3);
        LOGGER.debug("Department:{}", department);
        LOGGER.debug("EmployeeList:{}", department.getEmployeeList());
        LOGGER.info("End");
    }

    // ----------------------------------------------------------------------
    // Doc 2, Hands-on 6: @ManyToMany mapping between Employee and Skill
    // ----------------------------------------------------------------------

    // Employee 3 (Karthik) has no skills yet; skill 6 (Project Management) is not assigned to him.
    // Adds that skill to demonstrate the many-to-many relationship being updated.
    private static void testAddSkillToEmployee() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(3);
        Skill skill = skillService.get(6);

        employee.getSkillList().add(skill);
        employeeService.save(employee);

        LOGGER.debug("Employee after adding skill:{}", employee);
        LOGGER.debug("Employee Skills:{}", employee.getSkillList());
        LOGGER.info("End");
    }

    // ----------------------------------------------------------------------
    // Doc 3, Hands-on 2: HQL with fetch join (department + skillList in one query)
    // ----------------------------------------------------------------------

    private static void testGetAllPermanentEmployees() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        LOGGER.debug("Permanent Employees:{}", employees);
        employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
        LOGGER.info("End");
    }

    // ----------------------------------------------------------------------
    // Doc 3, Hands-on 4: HQL aggregate function (AVG)
    // ----------------------------------------------------------------------

    private static void testGetAverageSalary() {
        LOGGER.info("Start");
        double avgSalary = employeeService.getAverageSalary();
        LOGGER.debug("Average Salary (all employees):{}", avgSalary);
        LOGGER.info("End");
    }

    private static void testGetAverageSalaryByDepartment() {
        LOGGER.info("Start");
        double avgSalary = employeeService.getAverageSalary(3);
        LOGGER.debug("Average Salary (department id=3):{}", avgSalary);
        LOGGER.info("End");
    }

    // ----------------------------------------------------------------------
    // Doc 3, Hands-on 5: Native Query
    // ----------------------------------------------------------------------

    private static void testGetAllEmployeesNative() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllEmployeesNative();
        LOGGER.debug("All Employees (native query):{}", employees);
        LOGGER.info("End");
    }
}
