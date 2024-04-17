package com.example.dao;

import com.example.entity.Employee;
import com.example.util.EmployeeRowMapper;
import com.example.util.EmployeeResultSetExtractor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDAOTemplateImpl implements EmployeeDAO{

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertEmployee;

    public EmployeeDAOTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        insertEmployee = new SimpleJdbcInsert(dataSource)
                .withTableName("employee")
                .usingColumns("id","name","salary","departmentId");
    }

    @Override
    public Employee findById(int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        Employee emp = null;
        Object[] args = new Object[]{id};
        SqlRowSet rowset = jdbcTemplate.queryForRowSet(sql,args);
        if(rowset.next()){
            emp = new Employee();
            emp.setId(rowset.getInt("id"));
            emp.setName(rowset.getString("name"));
            emp.setSalary(rowset.getDouble("salary"));
            emp.setDepartmentId(rowset.getInt("departmentId"));
        }
        return emp;
    }
    public Employee findByIdMapper(int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        Object[] args = new Object[]{id};
        Employee employee = jdbcTemplate.queryForObject(
                sql,
                args,
                new BeanPropertyRowMapper<>(Employee.class)
        );
        return employee;
    }

    public Employee findByIdCustomMapper(int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        Object[] args = new Object[]{id};
        Employee employee = jdbcTemplate.queryForObject(
                sql,
                args,
                new EmployeeRowMapper()
        );
        return employee;
    }



    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employee";
        List<Employee> employees = new ArrayList<>();
        List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql);
        for(Map row: rows){
            Employee employee = new Employee();
            employee.setId((int)row.get("id"));
            employee.setName((String)row.get("name"));
            employee.setSalary((double)row.get("salary"));
            employee.setDepartmentId((int)row.get("departmentId"));
            employees.add(employee);
        }
        return employees;
    }

    public List<Employee> findAllbyExtractor() {
        String sql = "SELECT * FROM employee";
        List<Employee> employees = jdbcTemplate.query(sql,new EmployeeResultSetExtractor());
        return employees;
    }

    public List<Employee>  findAllbyRowMapper(){
        String sql = "SELECT * FROM employee";
        List<Employee> employees = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Employee.class));
        return employees;
    }

    @Override
    public void save(Employee entity) {

        String sql = "INSERT INTO employee (id,name,salary,departmentId) VALUES (?,?,?,?)";
        Object[] args = new Object[]{entity.getId(),entity.getName(),entity.getSalary(),entity.getDepartmentId()};
        jdbcTemplate.update(sql,args);
    }

    public void insertUsingSimpleInsert(Employee emp){
        Map<String,Object> params = new HashMap<>(4);
        params.put("id",emp.getId());
        params.put("name",emp.getName());
        params.put("salary",emp.getSalary());
        params.put("departmentId",emp.getDepartmentId());
        insertEmployee.execute(params);
    }

    @Override
    public void update(Employee entity) {

        String sql = "UPDATE employee SET name = ?, salary = ?,departmentId = ? WHERE id=?";
        Object[] args = new Object[]{entity.getName(),entity.getSalary(),entity.getDepartmentId(),entity.getId()};
        jdbcTemplate.update(sql,args);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM employee WHERE id =?";
        Object[] args = new Object[]{id};
        jdbcTemplate.update(sql,args);

    }

    @Override
    public int count() {
        String SQL = "SELECT count(*) from employee";
        int rowCount = jdbcTemplate.queryForObject(SQL,Integer.class);
        return rowCount;
    }

    @Override
    public int countbySalaryGreaterThan(int salary) {
        String SQL= "SELECT count(*) from employee where salary >= ?";
        int rowCount = jdbcTemplate.queryForObject(
                SQL,
                Integer.class,
                salary
                );

        return rowCount;
    }

    public void createAddressTable(){
        String sql = "DROP TABLE IF EXISTS address;\n" +
                "CREATE TABLE IF NOT EXISTS address (\n" +
                "    city VARCHAR(255)\n" +
                ");\n";
        jdbcTemplate.execute(sql);
    }
}
