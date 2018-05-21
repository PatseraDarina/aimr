package com.aimprosoft.task_1.controller;

import com.aimprosoft.task_1.bean.Employee;
import com.aimprosoft.task_1.controller.utils.ServletAttributes;
import com.aimprosoft.task_1.exception.TransactionInterruptedException;
import com.aimprosoft.task_1.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EmployeeServlet extends HttpServlet {

    private EmployeeService service;

    @Override
    public void init() throws ServletException {
        service = (EmployeeService) getServletContext().getAttribute(ServletAttributes.EMPLOYEE_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Employee> employees;
        try {
            employees = service.getAll();
            req.setAttribute(ServletAttributes.EMPLOYEES, employees);
            req.getRequestDispatcher(ServletAttributes.INDEX_JSP).forward(req, resp);
        } catch (TransactionInterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
