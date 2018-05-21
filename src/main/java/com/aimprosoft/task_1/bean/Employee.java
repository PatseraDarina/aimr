package com.aimprosoft.task_1.bean;

import com.aimprosoft.task_1.dao.AbstractEntity;

import java.sql.Date;

public class Employee extends AbstractEntity<Integer> {

    private String name;
    private String email;
    private Date date;
    private Integer idDepartment;

    public Employee() {
    }

    public Employee(String name, String email, Date date, Integer idDepartment) {
        this.name = name;
        this.email = email;
        this.date = date;
        this.idDepartment = idDepartment;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }
}
