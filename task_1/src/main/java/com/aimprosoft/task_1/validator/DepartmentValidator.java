package com.aimprosoft.task_1.validator;

import com.aimprosoft.task_1.bean.Department;
import com.aimprosoft.task_1.controller.utils.Validator;
import com.aimprosoft.task_1.utils.Constant;

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
