var rg_name = /^[A-Za-z ]{3,20}$/;
var rg_email = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
var rg_date = /([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))/;

function delete_department(id, rowid) {
    $.ajax({
        url: "department?departmentId=" + id,
        type: "DELETE",
        success: function(rowid) {
            document.getElementById("department_table").deleteRow(rowid);
        }
    });
}

function update_department(id, name, rowid) {
    $.ajax({
        url: "department?departmentId=" + id + "&departmentName=" + name,
        type: "PUT",
        success: function(rowid, name) {
            $('#edit').modal('show');
            document.getElementById("department_table").rows(rowid).cells[1]=name;
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
            return false;
        } else {
            $("#dNameErr").html("");
            return true;
        }
    }

    function validateDate() {
        if (!rg_name.test($("#employeeDate").val())) {
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
            {
                $("#eEmailErr").html("");
                return true;
            }
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


