package com.aimprosoft.task_1.dao;

public interface Entity<PK> {

    PK getId();

    void setId(PK id);
}
