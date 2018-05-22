package com.aimprosoft.task_1.dao.impl;

import com.aimprosoft.task_1.bean.Department;
import com.aimprosoft.task_1.dao.AbstractDao;
import com.aimprosoft.task_1.dao.DepartmentDao;
import com.aimprosoft.task_1.dao.parser.ResultSetParser;
import com.aimprosoft.task_1.utils.QueryStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoImpl extends AbstractDao<Department, Integer> implements DepartmentDao {

    public DepartmentDaoImpl(ResultSetParser<Department> resultSetParser) {
        super(resultSetParser);
    }

    @Override
    protected PreparedStatement prepareCreateQuery(Connection connection, Department entity) throws SQLException {
        String query = QueryStorage.Department.CREATE;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, entity.getName());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareReadQuery(Connection connection, Integer key) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement prepareUpdateQuery(Connection connection, Department entity) throws SQLException {
        return null;
    }

    @Override
    protected PreparedStatement prepareDeleteQuery(Connection connection, Integer key) throws SQLException {
        return null;
    }

    @Override
    public List<Department> readAll(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = prepareReadAllQuery(connection);
        return resultSetParser.getObjectList(preparedStatement.executeQuery());
    }

    private PreparedStatement prepareReadAllQuery(Connection connection) throws SQLException {
        String query = QueryStorage.Department.READ_ALL;
        return connection.prepareStatement(query);
    }

}
