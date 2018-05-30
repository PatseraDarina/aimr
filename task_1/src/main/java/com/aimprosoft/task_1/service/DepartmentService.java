package com.aimprosoft.task_1.service;

import com.aimprosoft.task_1.bean.Department;
import com.aimprosoft.task_1.dao.DepartmentDao;
import com.aimprosoft.task_1.dao.EmployeeDao;
import com.aimprosoft.task_1.exception.DataUniquenessException;
import com.aimprosoft.task_1.exception.TransactionInterruptedException;
import com.aimprosoft.task_1.transaction.TransactionManager;
import com.aimprosoft.task_1.utils.Constant;

import java.util.List;
import java.util.Objects;

public class DepartmentService {

    private TransactionManager transactionManager;
    private DepartmentDao departmentDao;
    private EmployeeDao employeeDao;

    public DepartmentService(TransactionManager transactionManager, DepartmentDao departmentDao, EmployeeDao employeeDao) {
        this.transactionManager = transactionManager;
        this.departmentDao = departmentDao;
        this.employeeDao = employeeDao;
    }

    public List<Department> getAll() throws TransactionInterruptedException {
        return transactionManager.doTransaction(departmentDao::readAll);
    }

    public void add(Department department) throws TransactionInterruptedException, DataUniquenessException {
        if (!isExist(department)) {
            transactionManager.doTransaction(connection -> {
                departmentDao.create(connection, department);
                return null;
            });
        } else {
            throw new DataUniquenessException(Constant.Exception.DEPARTMENT_NAME);
        }
    }

    public void delete(Integer id) throws TransactionInterruptedException {
        transactionManager.doTransaction(connection -> {
            employeeDao.deleteByDepartmentId(connection, id);
            departmentDao.delete(connection, id);
            return null;
        });
    }

    public void update(Department department) throws TransactionInterruptedException, DataUniquenessException {
        if (!isExist(department)) {
            transactionManager.doTransaction(connection -> {
                departmentDao.update(connection, department);
                return null;
            });
        } else {
            throw new DataUniquenessException(Constant.Exception.DEPARTMENT_NAME);
        }
    }

    public Department getByName(String name) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection -> departmentDao.readByName(connection, name));
    }

    private boolean isExist(Department department) throws TransactionInterruptedException {
        return (Objects.nonNull(transactionManager.doTransaction(connection ->
                departmentDao.readByName(connection, department.getName())
        )));
    }

    public Department getById(Integer id) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection -> departmentDao.read(connection, id));
    }
}
