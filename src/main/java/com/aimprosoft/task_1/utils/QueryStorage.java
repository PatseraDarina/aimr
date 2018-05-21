package com.aimprosoft.task_1.utils;

public final class QueryStorage {

    private QueryStorage() {

    }

    public static class Employee {
        public static final String CREATE = "INSERT INTO EMPLOYEE (NAME, EMAIL, DATE, ID_DEPARTMENT) VALUES (?, ?, ?, ?)";
        public static final String READ = "SELECT * FROM EMPLOYEE WHERE ID=?";
        public static final String UPDATE = "UPDATE EMPLOYEE SET NAME = ?, EMAIL = ?, DATE = ? ID_DEPARTMENT = ? WHERE ID = ?";
        public static final String DELETE = "DELETE FROM EMPLOYEE WHERE ID=?";
        public static final String READ_ALL = "SELECT * FROM EMPLOYEE";
        public static final String READ_ALL_BY_DEPARTMENT = "SELECT * FROM EMPLOYEE WHERE ID_DEPARTMENT = ?";
    }

    public static class Department {
        public static final String CREATE = "INSERT INTO DEPARTMENT (NAME) VALUES (?)";
        public static final String READ = "SELECT * FROM DEPARTMENT WHERE ID=?";
        public static final String UPDATE = "UPDATE DEPARTMENT SET NAME = ? WHERE ID = ?";
        public static final String DELETE = "DELETE FROM DEPARTMENT WHERE ID=?";
        public static final String READ_ALL = "SELECT * FROM DEPARTMENT";
    }
}
