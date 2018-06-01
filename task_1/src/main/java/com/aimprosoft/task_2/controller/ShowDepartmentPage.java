package com.aimprosoft.task_2.controller;

import com.aimprosoft.task_2.bean.Department;
import com.aimprosoft.task_2.exception.TransactionInterruptedException;
import com.aimprosoft.task_2.service.DepartmentService;
import com.aimprosoft.task_2.utils.Constant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/showDepartments")
public class ShowDepartmentPage extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ShowDepartmentPage.class);
    private DepartmentService departmentService;

    @Override
    public void init() {
        departmentService = (DepartmentService) getServletContext().getAttribute(Constant.Attribute.DEPARTMENT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean isAdd = Boolean.valueOf(req.getParameter(Constant.Attribute.IS_ADD));
        HttpSession session = req.getSession();
        session.setAttribute(Constant.Attribute.IS_ADD, isAdd);
        try {
            if (!isAdd) {
                Department department = departmentService.getById(Integer.valueOf(req.getParameter(Constant.Attribute.DEPARTMENT_ID)));
                req.setAttribute(Constant.Attribute.DEPARTMENT_NAME, department.getName());
            }
            req.getRequestDispatcher(Constant.JSP.ADD_DEPARTMENTS).forward(req, resp);
        } catch (TransactionInterruptedException e) {
            LOGGER.warn(e.getMessage());
            session.setAttribute(Constant.Attribute.ERROR_MESSAGE, e.getMessage());
            resp.sendRedirect(Constant.JSP.ERROR_PAGE);
        }
    }
}
