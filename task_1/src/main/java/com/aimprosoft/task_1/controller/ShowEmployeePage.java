package com.aimprosoft.task_1.controller;

import com.aimprosoft.task_1.bean.Department;
import com.aimprosoft.task_1.exception.TransactionInterruptedException;
import com.aimprosoft.task_1.service.DepartmentService;
import com.aimprosoft.task_1.utils.Constant;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/showEmployee")
public class ShowEmployeePage extends HttpServlet {

    private DepartmentService departmentService;
    private static final Logger LOGGER = Logger.getLogger(ShowDepartmentPage.class);

    @Override
    public void init() {
        departmentService = (DepartmentService) getServletContext().getAttribute(Constant.Attribute.DEPARTMENT_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean isAdd = Boolean.valueOf(req.getParameter(Constant.Attribute.IS_ADD));
        HttpSession session = req.getSession();
        try {
            if (Objects.nonNull(req.getParameter(Constant.Attribute.DEPARTMENT_ID))) {
                Department department = departmentService.getById(Integer.valueOf(req.getParameter(Constant.Attribute.DEPARTMENT_ID)));
                session.setAttribute(Constant.Attribute.DEPARTMENT_NAME, department.getName());
            }
        } catch (TransactionInterruptedException e) {
            LOGGER.warn(e.getMessage());
            session.setAttribute(Constant.Attribute.ERROR_MESSAGE, e.getMessage());
            resp.sendRedirect(Constant.JSP.ERROR_PAGE);
        }
        session.setAttribute(Constant.Attribute.IS_ADD, isAdd);
        req.getRequestDispatcher(Constant.JSP.ADD_EMPLOYEE).forward(req, resp);
    }
}
