package com.aimprosoft.task_1.controller;

import com.aimprosoft.task_1.bean.Department;
import com.aimprosoft.task_1.exception.DataUniquenessException;
import com.aimprosoft.task_1.exception.TransactionInterruptedException;
import com.aimprosoft.task_1.service.DepartmentService;
import com.aimprosoft.task_1.utils.Constant;
import com.aimprosoft.task_1.validator.DepartmentValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class DepartmentServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(DepartmentServlet.class);
    private DepartmentService departmentService;
    private DepartmentValidator departmentValidator;

    @Override
    public void init() {
        departmentService = (DepartmentService) getServletContext().getAttribute(Constant.Attribute.DEPARTMENT_SERVICE);
        departmentValidator = (DepartmentValidator) getServletContext().getAttribute(Constant.Attribute.DEPARTMENT_VALIDATOR);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        try {
            List<Department> departmentList = departmentService.getAll();
            session.setAttribute(Constant.Attribute.DEPARTMENTS, departmentList);
            session.setAttribute(Constant.Attribute.DEPARTMENT_NAME, departmentList.get(0).getName());
            req.getRequestDispatcher(Constant.JSP.INDEX).forward(req, resp);
        } catch (TransactionInterruptedException e) {
            session.setAttribute(Constant.Attribute.ERROR_MESSAGE, e.getMessage());
            resp.sendRedirect(Constant.JSP.ERROR_PAGE);
            LOGGER.warn(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(true);
        Department department = new Department();
        department.setName(req.getParameter(Constant.Attribute.DEPARTMENT_NAME));
        Map<String, String> errors = departmentValidator.validate(department);
        if (errors.isEmpty()) {
            try {
                departmentService.add(department);
                session.setAttribute(Constant.Attribute.INFO, Constant.Message.ADD_SUCCESS);
                resp.sendRedirect(Constant.JSP.ADD_DEPARTMENTS);
            } catch (TransactionInterruptedException e) {
                session.setAttribute(Constant.Attribute.ERROR_MESSAGE, e.getMessage());
                resp.sendRedirect(Constant.JSP.ERROR_PAGE);
                LOGGER.warn(e.getMessage(), e);
            } catch (DataUniquenessException e) {
                session.setAttribute(Constant.Attribute.INFO, e.getMessage());
                session.setAttribute(Constant.Attribute.DEPARTMENT, department);
                req.getRequestDispatcher(Constant.JSP.ADD_DEPARTMENTS).include(req, resp);
                LOGGER.warn(e.getMessage(), e);
            }
        } else {
            session.setAttribute(Constant.Attribute.ERRORS, errors);
            session.setAttribute(Constant.Attribute.DEPARTMENT, department);
            req.getRequestDispatcher(Constant.JSP.ADD_DEPARTMENTS).include(req, resp);
            errors.clear();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Department department = new Department();
        department.setId(Integer.valueOf(req.getParameter(Constant.Attribute.DEPARTMENT_ID)));
        try {
            departmentService.delete(department.getId());
        } catch (TransactionInterruptedException e) {
            session.setAttribute(Constant.Attribute.ERROR_MESSAGE, e.getMessage());
            LOGGER.warn(e.getMessage(), e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        Department department = new Department();
        department.setId(Integer.valueOf(req.getParameter(Constant.Attribute.DEPARTMENT_ID)));
        department.setName(req.getParameter(Constant.Attribute.DEPARTMENT_NAME));
        Map<String, String> errors = departmentValidator.validate(department);
        PrintWriter printWriter = resp.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        String info;
        if (errors.isEmpty()) {
            try {
                departmentService.update(department);
                info = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(Constant.Message.UPDATE_SUCCESS);
                printWriter.write(info);
            } catch (TransactionInterruptedException e) {
                session.setAttribute(Constant.Attribute.ERROR_MESSAGE, e.getMessage());
                LOGGER.warn(e.getMessage(), e);
            } catch (DataUniquenessException e) {
                info = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(e.getMessage());
                printWriter.write(info);
                session.setAttribute(Constant.Attribute.DEPARTMENT, department);
                LOGGER.warn(e.getMessage(), e);
            }
        } else {
            session.setAttribute(Constant.Attribute.ERRORS, errors);
            errors.clear();
        }
    }
}
