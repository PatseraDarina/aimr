package com.aimprosoft.task_1.dao.utils;

public final class QueryStorage {

    private QueryStorage() {

    }

    public static class Employee {
        public static final String CREATE = "INSERT INTO employee (NAME, SALARY, EMAIL, DATE, IDDEPARTMENT) VALUES (?, ?, ?, ?, ?)";
        public static final String READ = "SELECT * FROM employee WHERE ID=?";
        public static final String UPDATE = "UPDATE employee SET NAME = ?, SALARY = ?, EMAIL = ?, DATE = ?, IDDEPARTMENT  = ? WHERE ID = ?";
        public static final String DELETE = "DELETE FROM employee WHERE ID=?";
        public static final String READ_ALL = "SELECT * FROM employee";
        public static final String READ_ALL_BY_DEPARTMENT = "SELECT * FROM employee WHERE IDDEPARTMENT = ?";
        public static final String READ_BY_EMAIL = "SELECT * FROM employee WHERE EMAIL = ?";;
    }

    public static class Department {
        public static final String CREATE = "INSERT INTO department (NAME) VALUES (?)";
        public static final String READ = "SELECT * FROM department WHERE ID=?";
        public static final String UPDATE = "UPDATE department SET NAME = ? WHERE ID = ?";
        public static final String DELETE = "DELETE FROM department WHERE ID=?";
        public static final String READ_ALL = "SELECT * FROM department";
        public static final String READ_BY_NAME = "SELECT * FROM department WHERE NAME = ?";
    }
}
