package com.example.dao;

import com.example.entity.Employee;

public interface EmployeeDAO extends GenericDAO<Employee> {

    int count();
    int countbySalaryGreaterThan(int salary);
}
