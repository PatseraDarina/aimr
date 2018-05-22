package com.aimprosoft.task_1.utils;

public final class QueryStorage {

    private QueryStorage() {

    }

    public static class Employee {
        public static final String CREATE = "INSERT INTO employee (NAME, EMAIL, DATE, ID_DEPARTMENT) VALUES (?, ?, ?, ?)";
        public static final String READ = "ELECT * FROM employee WHERE ID=?";
        public static final String UPDATE = "UPDATE employee SET NAME = ?, EMAIL = ?, DATE = ? ID_DEPARTMENT = ? WHERE ID = ?";
        public static final String DELETE = "DELETE FROM employee WHERE ID=?";
        public static final String READ_ALL = "SELECT * FROM employee";
        public static final String READ_ALL_BY_DEPARTMENT = "SELECT * FROM employee WHERE ID_DEPARTMENT = ?";
    }

    public static class Department {
        public static final String CREATE = "INSERT INTO department (NAME) VALUES (?)";
        public static final String READ = "SELECT * ROM department WHERE ID=?";
        public static final String UPDATE = "UPDATE department SET NAME = ? WHERE ID = ?";
        public static final String DELETE = "DELETE FROM department WHERE ID=?";
        public static final String READ_ALL = "SELECT * FROM department";
    }
}
