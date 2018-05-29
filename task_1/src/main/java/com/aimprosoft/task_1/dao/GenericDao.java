package com.aimprosoft.task_1.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface GenericDao<T extends AbstractEntity, PK> {

    void create(Connection con, T entity) throws SQLException;
    T read(Connection con, PK id) throws SQLException;
    void update(Connection con, T entity) throws SQLException;
    void delete(Connection con, PK id) throws SQLException;

}
