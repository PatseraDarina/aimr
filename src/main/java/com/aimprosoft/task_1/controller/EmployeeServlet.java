package com.aimprosoft.task_1.controller;

import com.aimprosoft.task_1.bean.Employee;
import com.aimprosoft.task_1.exception.DataUniquenessException;
import com.aimprosoft.task_1.exception.TransactionInterruptedException;
import com.aimprosoft.task_1.service.DepartmentService;
import com.aimprosoft.task_1.service.EmployeeService;
import com.aimprosoft.task_1.utils.Constant;
import com.aimprosoft.task_1.validator.EmployeeValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private EmployeeValidator employeeValidator;
    private static final Logger LOGGER = Logger.getLogger(EmployeeServlet.class);

    @Override
    public void init() {
        employeeService = (EmployeeService) getServletContext().getAttribute(Constant.Attribute.EMPLOYEE_SERVICE);
        departmentService = (DepartmentService) getServletContext().getAttribute(Constant.Attribute.DEPARTMENT_SERVICE);
        employeeValidator = (EmployeeValidator) getServletContext().getAttribute(Constant.Attribute.EMPLOYEE_VALIDATOR);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer idDepartment = Integer.valueOf(req.getParameter(Constant.Attribute.DEPARTMENT_ID));
        try {
            List<Employee> employees = employeeService.getByIdDepartment(idDepartment);
            req.setAttribute(Constant.Attribute.EMPLOYEES, employees);
            req.getRequestDispatcher(Constant.JSP.EMPLOYEE).forward(req, resp);
        } catch (TransactionInterruptedException e) {
            session.setAttribute(Constant.Attribute.ERROR_MESSAGE, e.getMessage());
            resp.sendRedirect(Constant.JSP.ERROR_PAGE);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        Employee employee = new Employee();
        employee.setName(req.getParameter(Constant.Attribute.EMPLOYEE_NAME));
        employee.setEmail(req.getParameter(Constant.Attribute.EMPLOYEE_EMAIL));
        if (employeeValidator.validateDate(req.getParameter(Constant.Attribute.EMPLOYEE_DATE))) {
            employee.setDate(Date.valueOf(req.getParameter(Constant.Attribute.EMPLOYEE_DATE)));
        }
        if (employeeValidator.validateSalary(req.getParameter(Constant.Attribute.EMPLOYEE_SALARY))) {
            employee.setSalary(Integer.valueOf(req.getParameter(Constant.Attribute.EMPLOYEE_SALARY)));
        }
        try {
            employee.setIdDepartment(departmentService.getByName(req.getParameter(Constant.Attribute.DEPARTMENT_NAME)).getId());
            Map<String, String> errors = employeeValidator.validate(employee);
            if (errors.isEmpty()) {
                employeeService.add(employee);
                session.setAttribute(Constant.Attribute.INFO, Constant.Message.ADD_SUCCESS);
                req.getRequestDispatcher(Constant.JSP.ADD_EMPLOYEE).forward(req, resp);
            } else {
                session.setAttribute(Constant.Attribute.ERRORS, errors);
                session.setAttribute(Constant.Attribute.EMPLOYEE, employee);
                session.setAttribute(Constant.Attribute.DEPARTMENT_NAME, req.getParameter(Constant.Attribute.DEPARTMENT_NAME));
                req.getRequestDispatcher(Constant.JSP.ADD_EMPLOYEE).forward(req, resp);
                errors.clear();
            }
        } catch (TransactionInterruptedException e) {
            session.setAttribute(Constant.Attribute.ERROR_MESSAGE, e.getMessage());
            resp.sendRedirect(Constant.JSP.ERROR_PAGE);
            LOGGER.warn(e.getMessage(), e);
        } catch (DataUniquenessException e) {
            session.setAttribute(Constant.Attribute.INFO, e.getMessage());
            session.setAttribute(Constant.Attribute.EMPLOYEE, employee);
            session.setAttribute(Constant.Attribute.DEPARTMENT_NAME, req.getParameter(Constant.Attribute.DEPARTMENT_NAME));
            //resp.sendRedirect(Constant.Attribute.SHOW_EMPLOYEE);
            req.setAttribute(Constant.Attribute.DEPARTMENT_ID, employee.getIdDepartment());
            //req.getRequestDispatcher(Constant.Attribute.SHOW_EMPLOYEE).forward(req, resp);
            LOGGER.warn(e.getMessage(), e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        Employee employee = new Employee();
        employee.setName(req.getParameter(Constant.Attribute.EMPLOYEE_NAME));
        employee.setEmail(req.getParameter(Constant.Attribute.EMPLOYEE_EMAIL));
        if (employeeValidator.validateDate(req.getParameter(Constant.Attribute.EMPLOYEE_DATE))) {
            employee.setDate(Date.valueOf(req.getParameter(Constant.Attribute.EMPLOYEE_DATE)));
        }
        if (employeeValidator.validateSalary(req.getParameter(Constant.Attribute.EMPLOYEE_SALARY))) {
            employee.setSalary(Integer.valueOf(req.getParameter(Constant.Attribute.EMPLOYEE_SALARY)));
        }
        try {
            employee.setIdDepartment(departmentService.getByName(req.getParameter(Constant.Attribute.DEPARTMENT_NAME)).getId());
            Map<String, String> errors = employeeValidator.validate(employee);
            if (errors.isEmpty()) {
                employeeService.edit(employee);
                session.setAttribute(Constant.Attribute.INFO, Constant.Message.UPDATE_SUCCESS);
                req.getRequestDispatcher(Constant.JSP.ADD_EMPLOYEE).forward(req, resp);
            } else {
                session.setAttribute(Constant.Attribute.ERRORS, errors);
                session.setAttribute(Constant.Attribute.EMPLOYEE, employee);
                session.setAttribute(Constant.Attribute.DEPARTMENT_NAME, req.getParameter(Constant.Attribute.DEPARTMENT_NAME));
                req.getRequestDispatcher(Constant.JSP.ADD_EMPLOYEE).forward(req, resp);
                errors.clear();
            }
        } catch (TransactionInterruptedException e) {
            session.setAttribute(Constant.Attribute.ERROR_MESSAGE, e.getMessage());
            resp.sendRedirect(Constant.JSP.ERROR_PAGE);
            LOGGER.warn(e.getMessage(), e);
        } catch (DataUniquenessException e) {
            session.setAttribute(Constant.Attribute.INFO, e.getMessage());
            session.setAttribute(Constant.Attribute.EMPLOYEE, employee);
            session.setAttribute(Constant.Attribute.DEPARTMENT_NAME, req.getParameter(Constant.Attribute.DEPARTMENT_NAME));
            LOGGER.warn(e.getMessage(), e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Employee employee = new Employee();
        employee.setId(Integer.valueOf(req.getParameter(Constant.Attribute.EMPLOYEE_ID)));
        try {
            employeeService.delete(employee.getId());
        } catch (TransactionInterruptedException e) {
            session.setAttribute(Constant.Attribute.ERROR_MESSAGE, e.getMessage());
            resp.sendRedirect(Constant.JSP.ERROR_PAGE);
            LOGGER.warn(e.getMessage(), e);
        }
    }


}
