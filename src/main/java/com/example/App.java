package com.example;

import com.example.dao.*;
import com.example.entity.Department;
import com.example.entity.Employee;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // Load the Spring configuration file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        // Retrieve the EmployeeDAO bean from the context
        EmployeeDAO employeeDAO = context.getBean("employeeDAO", EmployeeDAOimpl.class);

        // Test the findById method
        Employee employee = employeeDAO.findById(1);
        if (employee != null) {
            System.out.println("Employee found: " + employee);
        } else {
            System.out.println("Employee not found.");
        }


        // Testing Spring JDBC template
        EmployeeDAOTemplateImpl employeeDAOTemplate = context.getBean("employeeDAOTemplate", EmployeeDAOTemplateImpl.class);
        System.out.println("Spring JDBC Template-------------------------");
        // Test findById method
        System.out.println("Testing findById method...");
        Employee employee1 = employeeDAOTemplate.findById(1);
        System.out.println("Employee found by id: " + employee1);

        // Test findByIdMapper method
        System.out.println("\nTesting findByIdMapper method...");
        Employee employee2 = employeeDAOTemplate.findByIdMapper(2);
        System.out.println("Employee found by id with BeanPropertyRowMapper: " + employee2);

        // Test findByIdCustomMapper method
        System.out.println("\nTesting findByIdCustomMapper method...");
        Employee employee3 = employeeDAOTemplate.findByIdCustomMapper(3);
        System.out.println("Employee found by id with custom RowMapper: " + employee3);

        // Test findAll method
        System.out.println("\nTesting findAll method...");
        List<Employee> allEmployees = employeeDAOTemplate.findAll();
        if (!allEmployees.isEmpty()) {
            System.out.println("All Employees:");
            for (Employee emp : allEmployees) {
                System.out.println(emp);
            }
        } else {
            System.out.println("No employees found.");
        }

        // Test findAllbyExtractor method
        System.out.println("\nTesting findAllbyExtractor method...");
        List<Employee> allEmployeesExtractor = employeeDAOTemplate.findAllbyExtractor();
        if (!allEmployeesExtractor.isEmpty()) {
            System.out.println("All Employees (Using ResultSetExtractor):");
            for (Employee emp : allEmployeesExtractor) {
                System.out.println(emp);
            }
        } else {
            System.out.println("No employees found.");
        }

        // Test findAllbyRowMapper method
        System.out.println("\nTesting findAllbyRowMapper method...");
        List<Employee> allEmployeesRowMapper = employeeDAOTemplate.findAllbyRowMapper();
        if (!allEmployeesRowMapper.isEmpty()) {
            System.out.println("All Employees (Using RowMapper):");
            for (Employee emp : allEmployeesRowMapper) {
                System.out.println(emp);
            }
        } else {
            System.out.println("No employees found.");
        }

        // Test save method
        System.out.println("\nTesting save method...");
        Employee newEmployee = new Employee(7232, "New Employee", 60000, 1);
        employeeDAOTemplate.save(newEmployee);
        System.out.println("New employee saved: " + newEmployee);

        // Test update method
        System.out.println("\nTesting update method...");
        newEmployee.setSalary(65000);
        employeeDAOTemplate.update(newEmployee);
        System.out.println("Employee updated: " + newEmployee);

        // Test delete method
        System.out.println("\nTesting delete method...");
        employeeDAOTemplate.delete(6001);
        System.out.println("Employee deleted with ID 600.");

        // Test count method
        System.out.println("\nTesting count method...");
        int count = employeeDAOTemplate.count();
        System.out.println("Total number of employees: " + count);

        // Test countbySalaryGreaterThan method
        System.out.println("\nTesting countbySalaryGreaterThan method...");
        int countBySalary = employeeDAOTemplate.countbySalaryGreaterThan(55000);
        System.out.println("Number of employees with salary greater than 55000: " + countBySalary);

//        // Test createAddressTable method
//        System.out.println("Testing createAddressTable method...");
//        employeeDAOTemplate.createAddressTable();
//        System.out.println("Address table created successfully.");
//

        // Testing Spring JDBC named parameter template
        EmployeeDAONamedParamTemplateImpl employeeDAONamedTemplate = context.getBean("employeeDAONamedParam", EmployeeDAONamedParamTemplateImpl.class);
        System.out.println("Spring JDBC Named Param Template-------------------------");

        // Test save method
        System.out.println("Testing save method...");
        Employee empl = new Employee(7, "New Employee", 70000, 1);
        employeeDAO.save(newEmployee);
        System.out.println("New employee saved: " + empl);

        // Test update method
        System.out.println("\nTesting update method...");
        empl.setSalary(75000);
        employeeDAO.update(empl);
        System.out.println("Employee updated: " + empl);

        // Retrieve the DepartmentDAO bean from the context
        DepartmentDAO departmentDAO = context.getBean("departmentDAO", DepartmentDAOimpl.class);

        // Test save method
        System.out.println("Testing save method...");
        Department newDepartment = new Department(501, "HR", "New York");
        departmentDAO.save(newDepartment);
        System.out.println("New department saved: " + newDepartment);

        // Test count method
        System.out.println("Testing count method...");
        int departmentCount = departmentDAO.count();
        System.out.println("Number of departments: " + departmentCount);

        // Create an Employee object
        Employee emp4 = new Employee();
        emp4.setId(80043);
        emp4.setName("John Doe");
        emp4.setSalary(50000);
        emp4.setDepartmentId(1);

        // Test insertUsingSimpleInsert method
        System.out.println("Testing insertUsingSimpleInsert method...");
        employeeDAOTemplate.insertUsingSimpleInsert(emp4);
        System.out.println("Employee inserted successfully: " + newEmployee);

        // Close the context
        context.close();
    }
}

