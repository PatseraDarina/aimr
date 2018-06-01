package com.aimprosoft.task_2.transaction;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.SQLException;

public interface Operation<T> {
    T execute(EntityManager entityManager);
}
