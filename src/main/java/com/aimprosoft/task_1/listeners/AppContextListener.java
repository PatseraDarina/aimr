package com.aimprosoft.task_1.listeners;

import com.aimprosoft.task_1.bean.Department;
import com.aimprosoft.task_1.bean.Employee;
import com.aimprosoft.task_1.controller.utils.ServletAttributes;
import com.aimprosoft.task_1.dao.DepartmentDao;
import com.aimprosoft.task_1.dao.EmployeeDao;
import com.aimprosoft.task_1.dao.impl.EmployeeDaoImpl;
import com.aimprosoft.task_1.dao.parser.ResultSetParser;
import com.aimprosoft.task_1.dao.parser.impl.DepartmentParser;
import com.aimprosoft.task_1.dao.parser.impl.EmployeeParser;
import com.aimprosoft.task_1.service.EmployeeService;
import com.aimprosoft.task_1.transaction.TransactionManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class AppContextListener implements ServletContextListener {

    private ServletContext servletContext;
    private DataSource dataSource;
    private TransactionManager transactionManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
        initDataSource();
        initTransactionManager();
        initService();
    }

    private void initDataSource() {
        try {
            Context initialContext = new InitialContext();
            Context envContext = (Context) initialContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/task_1");
        } catch (NamingException e) {
            throw new RuntimeException("Unable to instantiate dataSource: ", e);
        }
    }

    private void initTransactionManager() {
        transactionManager = new TransactionManager(dataSource);
    }

    private void initService() {
        initEmployeeService();
    }

    private void initEmployeeService() {
        ResultSetParser<Employee> resultSetParser = new EmployeeParser();
        EmployeeDao employeeDao = new EmployeeDaoImpl(resultSetParser);
        EmployeeService employeeService = new EmployeeService(transactionManager, employeeDao);
        servletContext.setAttribute(ServletAttributes.EMPLOYEE_SERVICE, employeeService);
    }

//    private void initDepartmentService() {
//        ResultSetParser<Department> resultSetParser = new DepartmentParser();
//        DepartmentDao departmentDao= new Dep(resultSetParser);
//        EmployeeService employeeService = new EmployeeService(transactionManager, employeeDao);
//        servletContext.setAttribute(ServletAttributes.EMPLOYEE_SERVICE, employeeService);
//    }
}
