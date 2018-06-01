package com.aimprosoft.task_2.bean;

import com.aimprosoft.task_2.dao.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.sql.Date;

@Entity
@Table(name = "employee", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Employee extends AbstractEntity<Integer> {

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "salary", nullable = false)
    private Integer salary;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "idDepartment", nullable = false)
    private Integer idDepartment;

    public Employee() {
    }

    public Employee(String name, Integer salary, String email, Date date, Integer idDepartment) {
        this.name = name;
        this.salary = salary;
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

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                ", date=" + date +
                ", idDepartment=" + idDepartment +
                '}';
    }
}
