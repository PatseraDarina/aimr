package com.aimprosoft.task_1.utils;

public final class Constant {

    private Constant() {

    }

    public static class JSP {
        public static final String ADD_DEPARTMENTS = "/addDepartment.jsp";
        public static final String INDEX = "/index.jsp";
        public static final String EMPLOYEE = "/employee.jsp";
        public static final String ADD_EMPLOYEE = "/addEmployee.jsp";
        public static final String ERROR_PAGE = "/error.jsp";
    }

    public static class Attribute {
        public static final String DEPARTMENT_NAME = "departmentName";
        public static final String DEPARTMENTS = "departments";
        public static final String EMPLOYEES = "employees";
        public static final String DEPARTMENT_SERVICE = "departmentService";
        public static final String EMPLOYEE_SERVICE = "employeeService";
        public static final String INFO = "info";
        public static final String ERRORS = "errors";
        public static final String DEPARTMENT_VALIDATOR = "departmentValidator";
        public static final String DEPARTMENT = "department";
        public static final String DEPARTMENT_ID = "departmentId";
        public static final String EMPLOYEE_EMAIL = "employeeEmail";
        public static final String EMPLOYEE_NAME = "employeeName";
        public static final String EMPLOYEE_VALIDATOR = "employeeValidator";
        public static final String EMPLOYEE_DATE = "employeeDate";
        public static final String EMPLOYEE = "employee";
        public static final String EMPLOYEE_ID = "employeeId";
        public static final String ERROR_MESSAGE = "errorMessage";
        public static final String IS_ADD = "isAdd";
    }

    public static class Message {
        public static final String ADD_SUCCESS = "Record was added successfully";
        public static final String INVALID_NAME = "Invalid name";
        public static final String INVALID_EMAIL = "Invalid email";
        public static final String INVALID_DATE = "Invalid date";
        public static final Object UPDATE_SUCCESS = "Record was updated successfully";
    }

    public static class Exception {
        public static final String NO_CONNECTION = "No connection with database";
        public static final String DEPARTMENT_NAME = "Department with this name already exists";
        public static final String EMPLOYEE_EMAIL = "Employee with this email already exists";
    }
}
