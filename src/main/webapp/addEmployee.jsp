<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta name="robots" content="noarchive"/>
    <script src="/js/jquery.min.js"></script>
    <script src="/js/main.js"></script>

    <title>Add employee</title>

</head>

<body>
    <div class="container">
        <section>
            <header class="major">
                <h2>New employee</h2>
            </header>

            <form name="employee-form" id="employee-form" action="/employee" method="post" onsubmit="return validateForm();">
                <div class="row half">
                    <div class="12u">
                        <p><input class="text" type="text" name="employeeName" id="employeeName" placeholder="Employee Name" pattern="^[A-Za-z ]{3,20}$" title="Ivan Ivanov" value="${sessionScope.employee.name}"/></p>
                        <span id="eNameErr"><c:out value="${sessionScope.errors['employeeName']}"/></span>
                        <p><input class="text" type="email" name="employeeEmail" id="employeeEmail" placeholder="Employee Email" value="${sessionScope.employee.email}"/></p>
                        <span id="eEmailErr"><c:out value="${sessionScope.errors['employeeEmail']}"/></span>
                        <p><input class="text" type="date" name="employeeDate" id="employeeDate" placeholder="Employee Date" pattern="([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))" title="2000-10-10" value="${empty sessionScope.employee.date ? '2000-01-01' : sessionScope.employee.date}"/></p>
                        <span id="eDateErr"><c:out value="${sessionScope.errors['employeeDate']}"/></span>
                        <c:out value="${sessionScope.employee.date}"/>
                        <p><select class="form-control" name="departmentName" id="departmentName">
                           <c:forEach  var="department" items="${sessionScope.departments}">
                            <c:choose>
                              <c:when test = "${department.name.equals(sessionScope.departmentName)}">
                                 <option selected="selected">${department.name}</option>
                              </c:when>
                              <c:otherwise>
                                 <option>${department.name}</option>
                              </c:otherwise>
                            </c:choose>
                           </c:forEach>
                       </select></p>
                    </div>
                </div>
                <button type="submit" form="employee-form" style = "margin-top: 2%">Add</button>
                <span><c:out value="${sessionScope.info}"/></span>
            </form>
            	<c:remove var="info" scope="session"/>
            	<c:remove var="errors" scope="session"/>
            	<c:remove var="employee" scope="session"/>
            	<c:remove var="departmentName" scope="session"/>
        </section>
    </div>
</div>

</body>
</html>
