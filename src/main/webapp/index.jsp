<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Departments</title>
</head>

<body>
    <h2>All employees</h2>
    <c:forEach var="emp" items="${employees}">
        <tr>
            <td>${emp.name}</td>
        </tr>
    </c:forEach>
</body>
</html>
