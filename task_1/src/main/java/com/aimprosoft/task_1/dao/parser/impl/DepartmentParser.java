package com.aimprosoft.task_1.dao.parser.impl;

import com.aimprosoft.task_1.bean.Department;
import com.aimprosoft.task_1.dao.parser.ResultSetParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentParser implements ResultSetParser<Department> {

    @Override
    public Department getObject(ResultSet resultSet) throws SQLException {
        Department department = null;
        if (resultSet.next()) {
            department = parseResultSet(resultSet);
        }
        return department;
    }

    @Override
    public List<Department> getObjectList(ResultSet resultSet) throws SQLException {
        List<Department> departments = new ArrayList<>();
        while (resultSet.next()) {
            departments.add(parseResultSet(resultSet));
        }
        return departments;
    }

    private Department parseResultSet(ResultSet resultSet) throws SQLException {
        Department department = new Department();
        department.setId(resultSet.getInt(1));
        department.setName(resultSet.getString(2));
        return department;
    }
}
