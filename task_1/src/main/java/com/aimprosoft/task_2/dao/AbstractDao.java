package com.aimprosoft.task_2.dao;

import com.aimprosoft.task_2.bean.AbstractEntity;

import javax.persistence.EntityManager;

public abstract class AbstractDao<PK, T extends AbstractEntity> implements GenericDao<PK, T> {

    protected EntityManager entityManager;

    protected AbstractDao() {

    }

    @Override
    public void create(EntityManager entityManager, T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T read(EntityManager entityManager, PK id, Class<T> clazz) {
        return entityManager.find(clazz, id);
    }

    @Override
    public void update(EntityManager entityManager, T entity) {
        entityManager.merge(entity);
    }

    @Override
    public void delete(EntityManager entityManager, T entity) {
        entityManager.remove(entity);
    }
}
