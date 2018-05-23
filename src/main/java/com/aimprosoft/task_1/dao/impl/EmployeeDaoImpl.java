package com.aimprosoft.task_1.dao.impl;

import com.aimprosoft.task_1.bean.Employee;
import com.aimprosoft.task_1.dao.AbstractDao;
import com.aimprosoft.task_1.dao.EmployeeDao;
import com.aimprosoft.task_1.dao.parser.ResultSetParser;
import com.aimprosoft.task_1.dao.utils.QueryStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoImpl extends AbstractDao<Employee, Integer> implements EmployeeDao {

    public EmployeeDaoImpl(ResultSetParser<Employee> resultSetParser) {
        super(resultSetParser);
    }

    @Override
    protected PreparedStatement prepareCreateQuery(Connection connection, Employee entity) throws SQLException {
        String query = QueryStorage.Employee.CREATE;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getEmail());
        preparedStatement.setDate(3, entity.getDate());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareReadQuery(Connection connection, Integer key) throws SQLException {
        String query = QueryStorage.Employee.READ;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, key);
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareUpdateQuery(Connection connection, Employee entity) throws SQLException {
        String query = QueryStorage.Employee.UPDATE;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, entity.getName());
        preparedStatement.setString(2, entity.getEmail());
        preparedStatement.setDate(3, entity.getDate());
        preparedStatement.setInt(4, entity.getId());
        return preparedStatement;
    }

    @Override
    protected PreparedStatement prepareDeleteQuery(Connection connection, Integer key) throws SQLException {
        String query = QueryStorage.Employee.DELETE;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, key);
        return preparedStatement;
    }


    @Override
    public List<Employee> readAll(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = prepareReadAll(connection);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSetParser.getObjectList(resultSet);
    }

    private PreparedStatement prepareReadAll(Connection connection) throws SQLException {
        String query = QueryStorage.Employee.READ_ALL;
        return connection.prepareStatement(query);
    }
}
