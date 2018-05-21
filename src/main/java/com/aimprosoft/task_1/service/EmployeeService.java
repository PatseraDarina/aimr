package com.aimprosoft.task_1.service;

import com.aimprosoft.task_1.bean.Employee;
import com.aimprosoft.task_1.dao.EmployeeDao;
import com.aimprosoft.task_1.exception.TransactionInterruptedException;
import com.aimprosoft.task_1.transaction.TransactionManager;

import java.util.List;

public class EmployeeService {

    private TransactionManager transactionManager;
    private EmployeeDao employeeDao;

    public EmployeeService(TransactionManager transactionManager, EmployeeDao employeeDao) {
        this.transactionManager = transactionManager;
        this.employeeDao = employeeDao;
    }

    public void add(Employee employee) throws TransactionInterruptedException {
        transactionManager.doTransaction(connection -> {
            employeeDao.create(connection, employee);
            return null;
        });
    }

    public Employee getById(Integer id) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
           employeeDao.read(connection, id)
        );
    }

    public void remove(Integer id) throws TransactionInterruptedException {
        transactionManager.doTransaction(connection -> {
            employeeDao.delete(connection, id);
            return null;
        });
    }

    public void edit(Employee employee) throws TransactionInterruptedException {
        transactionManager.doTransaction(connection -> {
            employeeDao.update(connection, employee);
            return null;
        });
    }

    public List<Employee> getAll() throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
            employeeDao.readAll(connection)
        );
    }

}
