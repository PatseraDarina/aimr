package com.aimprosoft.task_2.validator;

import com.aimprosoft.task_2.bean.Employee;
import com.aimprosoft.task_2.controller.utils.Validator;
import com.aimprosoft.task_2.utils.Constant;

import java.util.HashMap;
import java.util.Map;

public class EmployeeValidator {

    private Map<String, String> error = new HashMap<>();

    public Map<String, String> validate(Employee employee) {
        validateName(employee.getName());
        validateEmail(employee.getEmail());
        return error;
    }

    private void validateName(String name) {
        if (!Validator.isValidName(name)) {
            error.put(Constant.Attribute.EMPLOYEE_NAME, Constant.Message.INVALID_NAME);
        }
    }

    private void validateEmail(String email) {
        if (!Validator.isValidEmail(email)) {
            error.put(Constant.Attribute.EMPLOYEE_EMAIL, Constant.Message.INVALID_EMAIL);
        }
    }

    public boolean validateSalary(String salary) {
        if (!Validator.isValidSalary(salary)) {
            error.put(Constant.Attribute.EMPLOYEE_SALARY, Constant.Message.INVALID_SALARY);
            return false;
        }
        return true;
    }

    public boolean validateDate(String date) {
        if (!Validator.isValidDate(date)) {
            error.put(Constant.Attribute.EMPLOYEE_DATE, Constant.Message.INVALID_DATE);
            return false;
        }
        return true;
    }
}
