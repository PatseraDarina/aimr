package com.aimprosoft.task_1.bean;

import com.aimprosoft.task_1.dao.AbstractEntity;

public class Department extends AbstractEntity<Integer> {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
