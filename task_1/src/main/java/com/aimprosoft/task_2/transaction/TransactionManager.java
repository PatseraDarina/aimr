package com.aimprosoft.task_2.transaction;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Objects;

public class TransactionManager {

    private Logger LOGGER = Logger.getLogger(TransactionManager.class);
    private EntityManager entityManager;

    public TransactionManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public <T> T doTransaction(Operation<T> operation) {

        T result;
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            result = operation.execute(entityManager);
            transaction.commit();
        } catch (Exception ex) {
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }

        return result;
    }
}
