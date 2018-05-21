package com.aimprosoft.task_1.dao;

import com.aimprosoft.task_1.bean.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao extends GenericDao<Employee, Integer> {

    List<Employee> readAll(Connection connection) throws SQLException;

}
