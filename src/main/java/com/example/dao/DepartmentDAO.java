package com.example.dao;

import com.example.entity.Department;

public interface DepartmentDAO extends GenericDAO<Department> {

    public int count();

}
