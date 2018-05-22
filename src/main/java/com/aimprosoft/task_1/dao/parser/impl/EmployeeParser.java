package com.aimprosoft.task_1.dao.parser.impl;

import com.aimprosoft.task_1.bean.Employee;
import com.aimprosoft.task_1.dao.parser.ResultSetParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeParser implements ResultSetParser<Employee> {

    @Override
    public Employee getObject(ResultSet resultSet) throws SQLException {
        Employee employee = null;
        if (resultSet.next()) {
            employee = parseResultSet(resultSet);
        }
        return employee;
    }

    @Override
    public List<Employee> getObjectList(ResultSet resultSet) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            employees.add(parseResultSet(resultSet));
        }
        return employees;
    }

    private Employee parseResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt(1));
        employee.setName(resultSet.getString(2));
        employee.setEmail(resultSet.getString(3));
        employee.setDate(resultSet.getDate(4));
        employee.setIdDepartment(resultSet.getInt(5));
        return employee;
    }
}
