<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Cache-control" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="-1">
    <script src="/js/main.js"></script>

    <link href="<c:url value="/css/view_style.css" />" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Departments</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3>Departments</h3>
                <a href = "showDepartment?isAdd=true" target="_blank"><button style="border-radius: 5px"><img src="/img/icon.png" height="42" width="42"></button></a>
                <div class="table-responsive">
                <table id="department_table" class="table table-bordred table-striped">
                    <thead>
                        <th style="visibility: hidden">Id</th>
                        <th>Name</th>
                        <th>Employee list</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </thead>

                    <tbody>

                <c:forEach var="dep" items="${departments}">
                    <tr>
                        <td style="visibility: hidden"><c:out value="${dep.id}"/></td>
                        <td><c:out value="${dep.name}"/></td>
                        <td><p data-placement="top" data-toggle="tooltip" title="List">
                             <a href="employee?departmentId=${dep.id}" target="_blank"><button class="btn btn-primary btn-xs" data-title="List">Employee</span></button></a>
                          </p></td>
                        <td><p data-placement="top" data-toggle="tooltip" title="Edit"><a href="/showDepartment?departmentId=${dep.id}&departmentName=${dep.name}" target="_blank"><button class="btn btn-primary btn-xs" data-title="Edit"><span class="glyphicon glyphicon-pencil"></span></button></a></p></td>
                        <td><p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs" data-title="Delete" data-toggle="modal" data-target="#delete" ><span class="glyphicon glyphicon-trash"></span></button></p></td>
                    </tr>
                </c:forEach>
                    </tbody>
                </table>
                <div class="clearfix"></div>
            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                <h4 class="modal-title custom_align" id="Heading">Delete this entry</h4>
            </div>
            <div class="modal-body">
                <form name="delete-form" id="delete-form">
                    <div class="form-group">
                        <input class="form-control" type="hidden" name="departmentId" id="departmentId">
                        <input class="form-control" type="hidden" name="rowId" id="rowId">
                    </div>
                    <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?</div>

                    <div class="modal-footer ">
                        <button type="submit" class="btn btn-success" onclick="delete_department(document.getElementById('departmentId').value, document.getElementById('rowId').value)"><span class="glyphicon glyphicon-ok-sign"></span> Yes</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> No</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    var table = document.getElementById('department_table');
    for(var i = 1; i < table.rows.length; i++) {
            table.rows[i].onclick  = function() {
                document.getElementById("departmentId").value = this.cells[0].innerHTML;
                document.getElementById("rowId").value = this.rowIndex;
                document.getElementById("departmentName").value = this.cells[1].innerHTML;
        }
    }
</script>
</body>
</html>