package com.aimprosoft.task_2.validator;

import com.aimprosoft.task_2.bean.Department;
import com.aimprosoft.task_2.controller.utils.Validator;
import com.aimprosoft.task_2.utils.Constant;

import java.util.HashMap;
import java.util.Map;

public class DepartmentValidator {

    private Map<String, String> error = new HashMap<>();

    public Map<String, String> validate(Department department) {
        validateName(department.getName());
        return error;
    }

    private void validateName(String name) {
        if (!Validator.isValidName(name)) {
            error.put(Constant.Attribute.DEPARTMENT_NAME, Constant.Message.INVALID_NAME);
        }
    }
}
