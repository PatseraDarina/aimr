package com.aimprosoft.task_1.service;

import com.aimprosoft.task_1.dao.DepartmentDao;
import com.aimprosoft.task_1.transaction.TransactionManager;

public class DepartmentService {

    private TransactionManager transactionManager;
    private DepartmentDao departmentDao;

    public DepartmentService(TransactionManager transactionManager, DepartmentDao departmentDao) {
        this.transactionManager = transactionManager;
        this.departmentDao = departmentDao;
    }

}
