package com.example.dao;

import com.example.entity.Employee;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDAOimpl implements EmployeeDAO {
    private DataSource dataSource;

    public EmployeeDAOimpl() {
    }

    public EmployeeDAOimpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Employee findById(int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        Connection conn = null;
        Employee employee = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setSalary(resultSet.getDouble("salary"));
                employee.setDepartmentId(resultSet.getInt("departmentId"));
            }
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("Error Fetching employee by ID " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public void save(Employee entity) {
        String sql = "INSERT INTO EMPLOYEE(id,name,salary,departmentId) VALUES (?,?,?,?) ";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setInt(1, entity.getId());
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setDouble(3, entity.getSalary());
            preparedStatement.setInt(4, entity.getDepartmentId());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            System.out.println("Exception in Insert Employee" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void update(Employee entity) {
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public int countbySalaryGreaterThan(int salary) {
        return 0;
    }
}
