package com.aimprosoft.task_2.listeners;

import com.aimprosoft.task_2.dao.parser.ResultSetParser;
import com.aimprosoft.task_2.service.EmployeeService;
import com.aimprosoft.task_2.bean.Department;
import com.aimprosoft.task_2.bean.Employee;
import com.aimprosoft.task_2.dao.DepartmentDao;
import com.aimprosoft.task_2.dao.EmployeeDao;
import com.aimprosoft.task_2.dao.impl.DepartmentDaoImpl;
import com.aimprosoft.task_2.dao.impl.EmployeeDaoImpl;
import com.aimprosoft.task_2.dao.parser.impl.DepartmentParser;
import com.aimprosoft.task_2.dao.parser.impl.EmployeeParser;
import com.aimprosoft.task_2.service.DepartmentService;
import com.aimprosoft.task_2.transaction.TransactionManager;
import com.aimprosoft.task_2.utils.Constant;
import com.aimprosoft.task_2.validator.DepartmentValidator;
import com.aimprosoft.task_2.validator.EmployeeValidator;

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
    private EmployeeDao employeeDao;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
        initEmployeeDao();
        initDataSource();
        initTransactionManager();
        initService();
    }

    private void initDataSource() {
        try {
            Context initialContext = new InitialContext();
            Context envContext = (Context) initialContext.lookup(Constant.JAVA_COMP_ENV);
            dataSource = (DataSource) envContext.lookup(Constant.JDBC_TASK_1);
        } catch (NamingException e) {
            throw new RuntimeException(Constant.UNABLE_TO_INSTANTIATE_DATA_SOURCE, e);
        }
    }

    private void initTransactionManager() {
        transactionManager = new TransactionManager(dataSource);
    }

    private void initService() {
        initEmployeeService();
        initDepartmentService();
    }

    private void initEmployeeService() {
        EmployeeService employeeService = new EmployeeService(transactionManager, employeeDao);
        servletContext.setAttribute(Constant.Attribute.EMPLOYEE_SERVICE, employeeService);

        EmployeeValidator validator = new EmployeeValidator();
        servletContext.setAttribute(Constant.Attribute.EMPLOYEE_VALIDATOR, validator);
    }

    private void initDepartmentService() {
        ResultSetParser<Department> resultSetParser = new DepartmentParser();
        DepartmentDao departmentDao = new DepartmentDaoImpl(resultSetParser);
        DepartmentService departmentService = new DepartmentService(transactionManager, departmentDao, employeeDao);
        servletContext.setAttribute(Constant.Attribute.DEPARTMENT_SERVICE, departmentService);

        DepartmentValidator departmentValidator = new DepartmentValidator();
        servletContext.setAttribute(Constant.Attribute.DEPARTMENT_VALIDATOR, departmentValidator);
    }

    private void initEmployeeDao() {
        ResultSetParser<Employee> resultSetParser = new EmployeeParser();
        employeeDao = new EmployeeDaoImpl(resultSetParser);
    }

}
