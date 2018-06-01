package com.aimprosoft.task_2.dao;

import com.aimprosoft.task_2.bean.Department;

import java.util.List;

public interface DepartmentDao extends GenericDao<Integer, Department> {

    List<Department> readAll();
}
