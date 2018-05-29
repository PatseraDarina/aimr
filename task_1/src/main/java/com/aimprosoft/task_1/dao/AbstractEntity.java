package com.aimprosoft.task_1.dao;

public class AbstractEntity<PK> implements Entity<PK> {

    protected PK id;

    @Override
    public PK getId() {
        return id;
    }

    @Override
    public void setId(PK id) {
        this.id = id;
    }
}
