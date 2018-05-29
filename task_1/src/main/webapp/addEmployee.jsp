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
                <h2>${sessionScope.isAdd ? 'New employee' : 'Edit employee'}</h2>
            </header>
            <c:choose>
                <c:when test="${sessionScope.isAdd}">
                         <form name="employee-form" id="employee-form" action="/employee" method="post">
                        </c:when>
            <c:otherwise>
                <form name="employee-form" id="employee-form">
            </c:otherwise>
            </c:choose>
                <div class="row half">
                    <div class="12u">
                       <input class="form-control" type="hidden" name="employeeId" id="employeeId" value="${sessionScope.isAdd ? sessionScope.employee.id : param.employeeId}">
                        <p><input class="text" type="text" name="employeeName" id="employeeName" placeholder="Employee Name" pattern="^[A-Za-z ]{3,20}$" title="Ivan Ivanov" value="${sessionScope.isAdd ? sessionScope.employee.name : param.employeeName}"/></p>
                        <span id="eNameErr"><c:out value="${sessionScope.errors['employeeName']}"/></span>
                        <p><input class="text" type="text" name="employeeSalary" id="employeeSalary" placeholder="Employee Salary" pattern="^[0-9]{1,6}$" title="1-120000" value="${sessionScope.isAdd ? sessionScope.employee.salary : param.employeeSalary}"/></p>
                        <span id="eNameErr"><c:out value="${sessionScope.errors['employeeName']}"/></span>
                        <p><input class="text" type="email" name="employeeEmail" id="employeeEmail" placeholder="Employee Email" value="${sessionScope.isAdd ? sessionScope.employee.email : param.employeeEmail}"/></p>
                        <span id="eEmailErr"><c:out value="${sessionScope.errors['employeeEmail']}"/></span>
                        <p><input class="text" type="date" name="employeeDate" id="employeeDate" placeholder="Employee Date" pattern="([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))" title="2000-10-10" value="${sessionScope.isAdd ? sessionScope.employee.date : param.employeeDate}"/></p>
                        <span id="eDateErr"><c:out value="${sessionScope.errors['employeeDate']}"/></span>
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
                <c:choose>
                    <c:when test="${sessionScope.isAdd}">
                        <button type="submit" form="employee-form" style = "margin-top: 2%" onclick="return validateForm();">Add</button>
                    </c:when>
                <c:otherwise>
                        <button type="button" style = "margin-top: 2%" onclick="update_employee(document.getElementById('employeeId').value, document.getElementById('employeeName').value, document.getElementById('employeeSalary').value, document.getElementById('employeeEmail').value, document.getElementById('employeeDate').value, document.getElementById('departmentName').value)">Edit</button>
                </c:otherwise>
                </c:choose>
                <span id="info"><c:out value="${sessionScope.info}"/></span>
            </form>
            	<c:remove var="errors" scope="session"/>
            	<c:remove var="info" scope="session"/>
            	<c:remove var="employee" scope="session"/>
            	<c:remove var="departmentName" scope="session"/>
        </section>
    </div>
</div>

</body>
</html>
