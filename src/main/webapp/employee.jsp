<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="/js/main.js"></script>

    <link href="<c:url value="/css/view_style.css" />" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Employee</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3>Employee</h3>
                <a href = "addEmployee.jsp" target="_blank"><button style="border-radius: 5px"><img src="/img/icon.png" height="42" width="42"></button></a>
                <div class="table-responsive">
                <table id="mytable" class="table table-bordred table-striped">
                    <thead>
                        <th style="visibility: hidden">Id</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Date</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </thead>

                    <tbody>

                <c:forEach var="emp" items="${employees}">
                    <tr>
                        <td style="visibility: hidden"><c:out value="${emp.id}"/></td>
                        <td><c:out value="${emp.name}"/></td>
                        <td><c:out value="${emp.email}"/></td>
                        <td><c:out value="${emp.date}"/></td>
                        <td><p data-placement="top" data-toggle="tooltip" title="Edit"><button class="btn btn-primary btn-xs" data-title="Edit" data-toggle="modal" data-target="#edit" ><span class="glyphicon glyphicon-pencil"></span></button></p></td>
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

<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                <h4 class="modal-title custom_align" id="Heading">EDIT DEPARTMENT</h4>
            </div>
            <div class="modal-body">
                <form id="edit-form">
                <div class="form-group">
                    <input class="form-control" type="hidden" name="employeeId" id="employeeId">
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" name="departmentName" id="departmentName" value="${sessionScope.department.name}" required="required" >
                </div>
                    <span><c:out value="${sessionScope.info}"/></span>
                    <div class="modal-footer ">
                        <input type="submit" class="btn btn-success glyphicon glyphicon-upload"  aria-hidden="true" value="Update" onclick="update_department(document.getElementById('departmentId').value, document.getElementById('departmentName').value, document.getElementById('rowId').value)" style="width: 100%;"/>
                        <c:if test = "${(not empty sessionScope.errors) and (not empty sessionScope.info)}">
                            <script>$('#edit').modal('show');<script>
                        </c:if>
                    </div>
                </form>
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
                        <input class="form-control" type="hidden" name="employeeId" id="employeeId">
                        <input class="form-control" type="hidden" name="rowId" id="rowId">
                    </div>
                    <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?</div>

                    <div class="modal-footer ">
                        <button type="submit" class="btn btn-success" onclick="delete_department(document.getElementById('employeeId').value, document.getElementById('rowId').value)"><span class="glyphicon glyphicon-ok-sign"></span> Yes</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> No</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<script>
    var table = document.getElementById('mytable');
    for(var i = 1; i < table.rows.length; i++) {
            table.rows[i].onclick  = function() {
                document.getElementById("employeeId").value = this.cells[0].innerHTML;
                document.getElementById("rowId").value = this.rowIndex;
                document.getElementById("employeeName").value = this.cells[1].innerHTML;
        }
    }
</script>

</body>
</html>