package com.aimprosoft.task_2.bean;

import com.aimprosoft.task_1.dao.Entity;

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
