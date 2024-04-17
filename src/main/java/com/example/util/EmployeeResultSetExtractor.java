package com.example.util;

import com.example.entity.Employee;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class EmployeeResultSetExtractor implements ResultSetExtractor<List<Employee>> {
    @Override
    public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Employee> employees = new ArrayList<>();
        while (rs.next()){
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setName(rs.getString("name"));
            employee.setSalary(rs.getDouble("salary"));
            employee.setDepartmentId(rs.getInt("departmentId"));
            employees.add(employee);
        }
        return employees;
    }
}
