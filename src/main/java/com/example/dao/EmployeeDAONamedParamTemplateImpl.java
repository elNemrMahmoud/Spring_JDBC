package com.example.dao;

import com.example.entity.Employee;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class EmployeeDAONamedParamTemplateImpl implements EmployeeDAO{
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EmployeeDAONamedParamTemplateImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    @Override
    public int count() {
        return 0;
    }

    @Override
    public int countbySalaryGreaterThan(int salary) {
        return 0;
    }

    @Override
    public Employee findById(int id) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public void save(Employee entity) {
        String sql = "INSERT INTO employee (id, name, salary, departmentId) " +
                "VALUES (:id, :name, :salary, :departmentId)";
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(entity));

    }

    @Override
    public void update(Employee entity) {
        String sql = "UPDATE employee SET name = :name, salary = :salary, departmentId = :departmentId " +
                "WHERE id = :id";
        namedParameterJdbcTemplate.update(sql, new BeanPropertySqlParameterSource(entity));
    }

    @Override
    public void delete(int id) {

    }
}
