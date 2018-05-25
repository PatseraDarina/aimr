var rg_name = /^[A-Za-z ]{3,20}$/;
var rg_email = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
var rg_date = /^\s*3[01]|[12][0-9]|0?[1-9]\1[012]|0?[1-9]\?:19|20\d{2}\s*$/;

function delete_department(id, rowid) {
    $.ajax({
        url: "department?departmentId=" + id,
        type: "DELETE",
        success: function(rowid) {
            document.getElementById("department_table").deleteRow(rowid);
        }
    });
}

function update_department(departmentId, name) {
    $.ajax({
        url: "department?departmentName=" + name + "&departmentId=" + departmentId,
        type: "PUT",
        success: function(name) {
            document.getElementById("info").innerHTML="Record was updated successfully";
        }
    });
}

    function delete_employee(id, rowid, departmentId) {
        $.ajax({
            url: "employee?employeeId=" + id + "&departmentId" + departmentId,
            type: "DELETE",
            success: function(rowid) {
                document.getElementById("employee_table").deleteRow(rowid);
            }
        });
    }

    function update_employee(employeeName, employeeSalary, employeeEmail, employeeDate, departmentName) {
        $.ajax({
            url: "employee?employeeName=" + employeeName + "&employeeSalary=" + employeeSalary + "&employeeEmail=" + employeeEmail + "&employeeDate=" + employeeDate + "&departmentName=" + departmentName,
            type: "PUT",
            success: function(name) {
                document.getElementById("info").innerHTML="Record was updated successfully";
            }
        });
    }

    function validateName() {
        if (!rg_name.test($("#employeeName").val())) {
            document.getElementById("eNameErr").innerHTML = "Invalid name, should contain only latin at least 3 characters";
            return false;
        } else {
            $("#eNameErr").html("");
            return true;
        }
    }

    function validateDepName() {
        if (!rg_name.test($("#departmentName").val())) {
            document.getElementById("dNameErr").innerHTML = "Invalid name, should contain only latin at least 3 characters";
        } else {
            $("#dNameErr").html("");
            update_department(document.getElementById('departmentId').value, document.getElementById('departmentName').value);
        }
    }

    function validateDate() {
        if (!rg_date.test($("#employeeDate").val())) {
            document.getElementById("eDateErr").innerHTML = "Invalid date, should have format 2000-10-10";
            return false;
        } else {
            $("#eDateErr").html("");
            return true;
        }
    }

    function validateEmail() {
            if (!rg_email.test($("#employeeEmail").val())) {
                document.getElementById("eEmailErr").innerHTML = "Invalid email. Valid e-mail should contain only latin letters, numbers, '@' and '.'";
                return false;
        } else {
            $("#eEmailErr").html("");
            return true;
        }
    }

    function validateForm() {
        if (validateName() & validateEmail() & validateDate()) {
            return true;
         }
         return false;
    }

    $("#employeeName").focus(validateName);
    $("#employeeEmail").blur(validateEmail);
    $("#employeeDate").blur(validateDate);


