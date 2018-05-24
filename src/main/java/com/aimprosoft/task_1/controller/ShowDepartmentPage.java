package com.aimprosoft.task_1.controller;

import com.aimprosoft.task_1.utils.Constant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/showDepartment")
public class ShowDepartmentPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Boolean isAdd = Boolean.valueOf(req.getParameter(Constant.Attribute.IS_ADD));
        HttpSession session = req.getSession();
        session.setAttribute(Constant.Attribute.IS_ADD, isAdd);
        req.getRequestDispatcher(Constant.JSP.ADD_DEPARTMENTS).forward(req, resp);
    }
}
