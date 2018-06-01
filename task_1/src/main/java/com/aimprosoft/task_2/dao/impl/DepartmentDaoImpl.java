package com.aimprosoft.task_2.dao.impl;

import com.aimprosoft.task_1.dao.utils.QueryStorage;
import com.aimprosoft.task_2.bean.Department;
import com.aimprosoft.task_2.dao.AbstractDao;
import com.aimprosoft.task_2.dao.DepartmentDao;

import java.util.List;

public class DepartmentDaoImpl extends AbstractDao<Integer, Department> implements DepartmentDao {


    @Override
    public List<Department> readAll() {
        return entityManager.createQuery(QueryStorage.Department.READ_ALL).getResultList();
    }
}
