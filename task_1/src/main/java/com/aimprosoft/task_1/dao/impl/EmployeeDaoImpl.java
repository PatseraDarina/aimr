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
        preparedStatement.setInt(2, entity.getSalary());
        preparedStatement.setString(3, entity.getEmail());
        preparedStatement.setDate(4, entity.getDate());
        preparedStatement.setInt(5, entity.getIdDepartment());
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
        preparedStatement.setInt(2, entity.getSalary());
        preparedStatement.setString(3, entity.getEmail());
        preparedStatement.setDate(4, entity.getDate());
        preparedStatement.setInt(5, entity.getIdDepartment());
        preparedStatement.setInt(6, entity.getId());
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

    @Override
    public List<Employee> readByIdDepartment(Connection connection, Integer idDepartment) throws SQLException {
        PreparedStatement preparedStatement = prepareReadByIdDepartment(connection, idDepartment);
        return resultSetParser.getObjectList(preparedStatement.executeQuery());
    }

    @Override
    public Employee readByEmail(Connection connection, String email) throws SQLException {
        PreparedStatement preparedStatement = prepareReadByEmailQuery(connection, email);
        return resultSetParser.getObject(preparedStatement.executeQuery());
    }

    @Override
    public void deleteByDepartmentId(Connection connection, Integer id) throws SQLException {
        PreparedStatement preparedStatement = prepareDeleteByDepartmentQuery(connection, id);
        preparedStatement.executeUpdate();
    }

    private PreparedStatement prepareDeleteByDepartmentQuery(Connection connection, Integer id) throws SQLException {
        String query = QueryStorage.Employee.DELETE_BY_DEPARTMENT;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        return preparedStatement;
    }

    private PreparedStatement prepareReadByEmailQuery(Connection connection, String email) throws SQLException {
        String query = QueryStorage.Employee.READ_BY_EMAIL;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        return preparedStatement;
    }

    private PreparedStatement prepareReadByIdDepartment(Connection connection, Integer idDepartment) throws SQLException {
        String query = QueryStorage.Employee.READ_ALL_BY_DEPARTMENT;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idDepartment);
        return preparedStatement;
    }

    private PreparedStatement prepareReadAll(Connection connection) throws SQLException {
        String query = QueryStorage.Employee.READ_ALL;
        return connection.prepareStatement(query);
    }
}
