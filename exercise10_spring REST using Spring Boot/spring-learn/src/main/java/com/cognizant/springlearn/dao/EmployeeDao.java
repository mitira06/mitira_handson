package com.cognizant.springlearn.dao;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.cognizant.springlearn.Employee;
import com.cognizant.springlearn.service.exception.EmployeeNotFoundException;

@Repository
public class EmployeeDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

	private static ArrayList<Employee> EMPLOYEE_LIST;

	@SuppressWarnings("unchecked")
	public EmployeeDao() {
		LOGGER.info("START");

		ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
		EMPLOYEE_LIST = (ArrayList<Employee>) context.getBean("employeeList", ArrayList.class);

		LOGGER.info("END");
	}

	public ArrayList<Employee> getAllEmployees() {
		LOGGER.info("START");
		LOGGER.info("END");
		return EMPLOYEE_LIST;
	}

	/**
	 * Doc 4: Hands on - Implement REST service for updating an employee.
	 * Replaces the matching employee (by id) in the list, or throws
	 * EmployeeNotFoundException if no employee with that id exists.
	 */
	public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException {
		LOGGER.info("START");
		LOGGER.debug("employee: {}", employee);

		for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {
			if (EMPLOYEE_LIST.get(i).getId().equals(employee.getId())) {
				EMPLOYEE_LIST.set(i, employee);
				LOGGER.info("END");
				return employee;
			}
		}

		throw new EmployeeNotFoundException("Employee not found");
	}

	/**
	 * Doc 4: Hands on - Implement REST DELETE Service.
	 * Removes the employee with the given id, or throws
	 * EmployeeNotFoundException if no employee with that id exists.
	 */
	public void deleteEmployee(Long id) throws EmployeeNotFoundException {
		LOGGER.info("START");
		LOGGER.debug("id: {}", id);

		boolean removed = EMPLOYEE_LIST.removeIf(employee -> employee.getId().equals(id));

		if (!removed) {
			throw new EmployeeNotFoundException("Employee not found");
		}

		LOGGER.info("END");
	}

}
