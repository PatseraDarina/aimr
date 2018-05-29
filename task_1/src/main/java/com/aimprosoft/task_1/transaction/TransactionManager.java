package com.aimprosoft.task_1.transaction;

import com.aimprosoft.task_1.exception.TransactionInterruptedException;
import com.aimprosoft.task_1.utils.Constant;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class TransactionManager {

    private Logger LOGGER = Logger.getLogger(TransactionManager.class);
    private DataSource dataSource;

    public TransactionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> T doTransaction(Operation<T> operation) throws TransactionInterruptedException {

        T result;
        Connection connection;

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new TransactionInterruptedException(e.getMessage(), e);
        }
        String exceptionMessage;
        try {
            if (Objects.nonNull(connection)) {
                connection.setAutoCommit(false);
                result = operation.execute(connection);
                connection.commit();
                return result;
            }
            exceptionMessage = Constant.Exception.NO_CONNECTION;
        } catch (SQLException e) {
            exceptionMessage = e.getMessage();
            exceptionMessage = rollback(connection, exceptionMessage);
        } finally {
            try {
                if (Objects.nonNull(connection)) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.warn(e.getMessage(), e);
            }
        }
        throw new TransactionInterruptedException(exceptionMessage);
    }

    private String rollback(Connection connection, String exceptionMessage) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                exceptionMessage += System.lineSeparator() + e.getMessage();
            }
        }
        return exceptionMessage;
    }
}
