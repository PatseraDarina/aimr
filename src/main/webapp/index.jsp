<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="/js/check_all.js"></script>

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
                <button style="border-radius: 5px" onclick="location.href='/jsp/addDepartment.jsp'"><img src="/img/icon.png" height="42" width="42"></button>
                <div class="table-responsive">
                <table id="mytable" class="table table-bordred table-striped">
                    <thead>
                      <%--  <th><input type="checkbox" id="checkall" /></th>--%>
                        <th style="visibility: hidden">Id</th>
                        <th>Name</th>
                        <th>Employee list</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </thead>

                    <tbody>

                <c:forEach var="dep" items="${departments}">
                    <tr>
                       <%-- <td><input type="checkbox" class="checkthis" /></td>--%>
                        <td style="visibility: hidden"><c:out value="${dep.id}"/></td>
                        <td> <c:out value="${dep.name}"/></td>
                         <td><p data-placement="top" data-toggle="tooltip" title="List"><button class="btn btn-primary btn-xs" data-title="List" data-toggle="modal" data-target="#edit" >Employee</span></button></p></td>
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
                <h4 class="modal-title custom_align" id="Heading">EDIT PERIODICAL</h4>
            </div>
            <div class="modal-body">
                <form action="/updatePeriodicals" id="edit-form" method="post">
                <div class="form-group">
                    <input class="form-control " type="hidden" name="periodicalId" id="idP" required="required">
                </div>
                <div class="form-group">
                    <select class="form-control" id="categoryP" name="periodicalCategory">
                        <c:forEach  var="category" items="${categoryList}">
                            <option> ${category.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <input class="form-control " type="text" name="periodicalName" id="nameP" required="required" >
                </div>
                <div class="form-group">
                    <input class="form-control " type="number" min="1"  step="0.001" name="periodicalPrice" id="priceP" required="required" placeholder="Price">
                </div>
                <div class="form-group">
                    <p>Photo <input type="file" accept="image/jpeg" name="periodicalPhoto" id="photoP"  required="required" />
                </div>
                    <div class="modal-footer ">
                        <input type="submit" class="btn btn-success glyphicon glyphicon-upload"  aria-hidden="true" value="Update" form="edit-form" style="width: 100%;"/>
                    </div>
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                <h4 class="modal-title custom_align" id="Heading">Delete this entry</h4>
            </div>
            <div class="modal-body">
                <form action="/deletePeriodicals" method="post" id="delete-form">
                    <div class="form-group">
                        <input class="form-control " type="hidden" name="periodicalId" id="idPDel" required="required">
                    </div>
                    <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you want to delete this Record?</div>

                    <div class="modal-footer ">
                        <button type="submit" class="btn btn-success" form="delete-form"><span class="glyphicon glyphicon-ok-sign"></span> Yes</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> No</button>
                    </div>
                </form>
        </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="add" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                <h4 class="modal-title custom_align" id="Heading">ADD PERIODICAL</h4>
            </div>
            <div class="modal-body">
                <form action="/addPeriodicals" id="add-form" method="post">
                    <div class="form-group">
                        <input class="form-control " type="hidden" name="periodicalId" required="required">
                    </div>
                    <div class="form-group">
                        <select class="form-control" name="periodicalCategory">
                            <c:forEach  var="category" items="${categoryList}">
                                <option> ${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <input class="form-control " type="text" name="periodicalName" required="required" placeholder="Name">
                    </div>
                    <div class="form-group">
                        <input class="form-control " type="number" min="1"  step="0.001" name="periodicalPrice" required="required" placeholder="Price">
                    </div>
                    <div class="form-group">
                        <p>Photo <input type="file" accept="image/jpeg" name="periodicalPhoto"  required="required" />
                    </div>
                    <div class="modal-footer ">
                        <input type="submit" class="btn-primary" aria-hidden="true" value="ADD" form="add-form" style="width: 100%;"/>
                    </div>
                </form>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<script>
    var table = document.getElementById('mytable');
    for(var i = 1; i < table.rows.length; i++) {
            table.rows[i].onclick  = function() {
                document.getElementById("idPDel").value = this.cells[0].innerHTML;
                document.getElementById("idP").value = this.cells[0].innerHTML;
                document.getElementById("categoryP").value = this.cells[1].innerHTML;
                document.getElementById("nameP").value = this.cells[2].innerHTML;
                document.getElementById("priceP").value = Number(this.cells[3].innerHTML);
                document.getElementById("photoP").value =  this.cells[4].innerHTML;
        }
    }
</script>

</body>
</html>