package com.aimprosoft.task_1.controller;

import com.aimprosoft.task_1.bean.Department;
import com.aimprosoft.task_1.controller.utils.ServletAttributes;
import com.aimprosoft.task_1.exception.TransactionInterruptedException;
import com.aimprosoft.task_1.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DepartmentServlet extends HttpServlet {

    private DepartmentService departmentService;

    @Override
    public void init() {
        departmentService = (DepartmentService) getServletContext().getAttribute(ServletAttributes.DEPARTMENT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Department> departmentList = departmentService.getAll();
            req.setAttribute(ServletAttributes.DEPARTMENTS, departmentList);
            req.getRequestDispatcher(ServletAttributes.INDEX_JSP).forward(req, resp);
        } catch (TransactionInterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Department department = new Department();
            departmentService.add(department);
        } catch (TransactionInterruptedException e) {
            e.printStackTrace();
        }
    }
}
