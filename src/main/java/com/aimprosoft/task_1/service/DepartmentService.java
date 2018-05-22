package com.aimprosoft.task_1.service;

import com.aimprosoft.task_1.bean.Department;
import com.aimprosoft.task_1.dao.DepartmentDao;
import com.aimprosoft.task_1.exception.TransactionInterruptedException;
import com.aimprosoft.task_1.transaction.TransactionManager;

import java.util.List;

public class DepartmentService {

    private TransactionManager transactionManager;
    private DepartmentDao departmentDao;

    public DepartmentService(TransactionManager transactionManager, DepartmentDao departmentDao) {
        this.transactionManager = transactionManager;
        this.departmentDao = departmentDao;
    }

    public List<Department> getAll() throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection -> departmentDao.readAll(connection));
    }

    public void add(Department department) throws TransactionInterruptedException {
        transactionManager.doTransaction(connection -> {
            departmentDao.create(connection, department);
            return null;
        });
    }

}
