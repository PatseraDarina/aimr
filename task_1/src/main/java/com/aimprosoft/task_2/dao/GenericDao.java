package com.aimprosoft.task_2.dao;

import com.aimprosoft.task_1.dao.Entity;

import javax.persistence.EntityManager;

public interface GenericDao<PK, T extends Entity> {

    void create(EntityManager entityManager, T entity);
    T read(EntityManager entityManager, PK id, Class<T> clazz);
    void update(EntityManager entityManager, T entity);
    void delete(EntityManager entityManager, T entity);
}
