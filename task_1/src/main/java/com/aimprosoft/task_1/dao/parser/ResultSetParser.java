package com.aimprosoft.task_1.dao.parser;

import com.aimprosoft.task_1.dao.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ResultSetParser<T extends Entity> {

    T getObject(ResultSet resultSet) throws SQLException;
    List<T> getObjectList(ResultSet resultSet) throws  SQLException;
}
