<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>

    <meta http-equiv="Cache-control" content="no-cache">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="-1">

    <title>Add departments</title>

    <script src="js/jquery.min.js"></script>
    <script src="js/main.js"></script>

    <noscript>
        <link rel="stylesheet" href="css/skel.css" />
        <link rel="stylesheet" href="css/style.css" />
        <link rel="stylesheet" href="css/style-wide.css" />
    </noscript>

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
                    <div class="row half">
                        <div class="12u">
                            <input class="text" type="text" name="departmentName" id="departmentName" placeholder="Department Name" value="${sessionScope.department.name}"/>
                            <span id="dNameErr"><c:out value="${sessionScope.errors['departmentName']}"/></span>
                        </div>
                    </div>
                        <button style = "margin-top: 2%" onclick="return validateDepName();">Add</button>
                        <span><c:out value="${sessionScope.info}"/></span>
                      </form>
                        </c:when>
                    <c:otherwise>
                     <div class="row half">
                        <div class="12u">
                            <input class="form-control" type="hidden" name="rowId" id="rowId" value='${param.rowId}'>
                            <input class="form-control" type="hidden" name="departmentId" id="departmentId" value='${param.departmentId}'>
                            <input class="text" type="text" name="departmentName" id="departmentName" placeholder="Department Name" value="${param.departmentName}"/>
                            <span id="dNameErr"><c:out value="${sessionScope.errors['departmentName']}"/></span>
                        </div>
                      </div>
                            <button style = "margin-top: 2%" onclick="return validateDepName();">Edit</button></a>
                            <span><c:out value="${sessionScope.info}"/></span>
                    </c:otherwise>
                    </c:choose>
            	<c:remove var="info" scope="session"/>
            	<c:remove var="errors" scope="session"/>
            	<c:remove var="department" scope="session"/>
        </section>
    </div>
</div>

</body>
</html>
