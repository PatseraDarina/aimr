package com.aimprosoft.task_1.dao;

import com.aimprosoft.task_1.dao.parser.ResultSetParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDao<T extends AbstractEntity, PK> implements GenericDao<T, PK> {

    protected ResultSetParser<T> resultSetParser;

    protected abstract PreparedStatement prepareCreateQuery(Connection connection, T entity) throws SQLException;

    protected abstract PreparedStatement prepareReadQuery(Connection connection, PK key) throws SQLException;

    protected abstract PreparedStatement prepareUpdateQuery(Connection connection, T entity) throws SQLException;

    protected abstract PreparedStatement prepareDeleteQuery(Connection connection, PK key) throws SQLException;

    public AbstractDao(ResultSetParser<T> resultSetParser) {
        this.resultSetParser = resultSetParser;
    }

    @Override
    public void create(Connection con, T entity) throws SQLException {
        PreparedStatement preparedStatement = prepareCreateQuery(con, entity);
        preparedStatement.executeUpdate();
    }

    @Override
    public T read(Connection con, PK id) throws SQLException {
        PreparedStatement preparedStatement = prepareReadQuery(con, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSetParser.getObject(resultSet);
    }

    @Override
    public void update(Connection con, T entity) throws SQLException {
        PreparedStatement preparedStatement = prepareUpdateQuery(con, entity);
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Connection con, PK id) throws SQLException {
        PreparedStatement preparedStatement = prepareDeleteQuery(con, id);
        preparedStatement.executeUpdate();
    }
}
