package com.example.dao;

import com.example.entity.Department;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


import javax.sql.DataSource;
import java.util.List;

public class DepartmentDAOimpl extends JdbcDaoSupport implements DepartmentDAO{



    public DepartmentDAOimpl() {
    }

    @Override
    public Department findById(int id) {
        return null;
    }

    @Override
    public List<Department> findAll() {
        return null;
    }

    @Override
    public void save(Department entity) {
        String sql = "INSERT into department (id,name,location) VALUES (?,?,?)";
        Object[] args = new Object[]{entity.getId(),entity.getName(),entity.getLocation()};
        getJdbcTemplate().update(sql,args);
    }

    @Override
    public void update(Department entity) {
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public int count() {
        String sql = "SELECT count(*) FROM department";
        int rowCount = getJdbcTemplate().queryForObject(sql,Integer.class);
        return rowCount;
    }
}
