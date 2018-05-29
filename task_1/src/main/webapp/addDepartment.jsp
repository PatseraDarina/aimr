<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>

    <meta http-equiv="Cache-control" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="-1">

    <title>${sessionScope.isAdd ? 'Add ' : 'Edit '}department</title>

    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>

</head>
<body>
    <div class="container">
        <section>
            <header class="major">
                <h2>${sessionScope.isAdd ? 'New department' : 'Edit department'}</h2>
            </header>
            <c:choose>
                <c:when test="${sessionScope.isAdd}">
                    <form name="department-form" id="department-form" method="POST" action = "/department">
                 </c:when>
                 <c:otherwise>
                     <form name="department-form" id="department-form">
                  </c:otherwise>
            </c:choose>
                    <div class="row half">
                        <div class="12u">
                           <input class="form-control" type="hidden" name="rowId" id="rowId" value='${param.rowId}'>
                           <input class="form-control" type="hidden" name="departmentId" id="departmentId" value='${param.departmentId}'>
                           <input class="text" type="text" name="departmentName" id="departmentName" placeholder="Department Name" value='${empty sessionScope.department and not isAdd ? param.departmentName : sessionScope.department.name}'/>
                           <span id="dNameErr">${session.errors['departmentName']}</span>
                           <span id="info">${sessionScope.info}</span>
                           <c:remove var="info" scope="session"/>
                           <c:remove var="department" scope="session"/>
                           <c:remove var="departmentName" scope="request"/>
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${sessionScope.isAdd}">
                            <button type="submit" form="department-form" style = "margin-top: 2%" onclick="return validateDepName();">Add</button>
                         </c:when>
                         <c:otherwise>
                             <button type="button" form="department-form" style = "margin-top: 2%" onclick="return update_department(document.getElementById('departmentId').value, document.getElementById('departmentName').value);">Edit</button>
                          </c:otherwise>
                    </c:choose>
                    </form>
        </section>
    </div>
</div>
</body>
</html>
