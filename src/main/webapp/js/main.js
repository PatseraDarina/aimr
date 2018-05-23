    function delete_department(id, rowid) {
        $.ajax({
            url: "department?departmentId=" + id,
            type: "DELETE",
            success: function(rowid) {
                document.getElementById("mytable").deleteRow(rowid);
            }
        });
    }

    function update_department(id, name, rowid) {
        $.ajax({
            url: "department?departmentId=" + id + "&departmentName=" + name,
            type: "PUT",
            success: function(rowid, name) {
                $('#edit').modal('show');
                document.getElementById("mytable").rows(rowid).cells[1]=name;
            }
        });
    }

