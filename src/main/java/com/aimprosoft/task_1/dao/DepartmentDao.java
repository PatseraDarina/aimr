package com.aimprosoft.task_1.dao;

import com.aimprosoft.task_1.bean.Department;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DepartmentDao extends GenericDao<Department, Integer> {

    List<Department> readAll(Connection connection) throws SQLException;

}
