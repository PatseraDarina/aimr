package com.aimprosoft.task_1.service;

import com.aimprosoft.task_1.bean.Employee;
import com.aimprosoft.task_1.dao.EmployeeDao;
import com.aimprosoft.task_1.exception.DataUniquenessException;
import com.aimprosoft.task_1.exception.TransactionInterruptedException;
import com.aimprosoft.task_1.transaction.TransactionManager;
import com.aimprosoft.task_1.utils.Constant;

import java.util.List;
import java.util.Objects;

public class EmployeeService {

    private TransactionManager transactionManager;
    private EmployeeDao employeeDao;

    public EmployeeService(TransactionManager transactionManager, EmployeeDao employeeDao) {
        this.transactionManager = transactionManager;
        this.employeeDao = employeeDao;
    }

    public void add(Employee employee) throws TransactionInterruptedException, DataUniquenessException {
        if (!isExist(employee)) {
            transactionManager.doTransaction(connection -> {
                employeeDao.create(connection, employee);
                return null;
            });
        } else {
            throw new DataUniquenessException(Constant.Exception.EMPLOYEE_EMAIL);
        }

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

    public List<Employee> getByIdDepartment(Integer idDepartment) throws TransactionInterruptedException {
        return transactionManager.doTransaction(connection ->
                employeeDao.readByIdDepartment(connection, idDepartment));
    }

    private boolean isExist(Employee employee) throws TransactionInterruptedException {
        return (Objects.nonNull(transactionManager.doTransaction(connection ->
                employeeDao.readByEmail(connection, employee.getEmail())
        )));
    }

    public void delete(Integer id) throws TransactionInterruptedException {
        transactionManager.doTransaction(connection -> {
            employeeDao.delete(connection, id);
            return null;
        });
    }
}
