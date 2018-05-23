<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>

    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta name="robots" content="noarchive"/>

    <title>Add departments</title>

    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.dropotron.min.js"></script>
    <script src="js/skel.min.js"></script>
    <script src="js/init.js"></script>

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
                <h2>New department</h2>
            </header>

            <form name="department-form" id="department-form" action="/department" method="post" onsubmit="return validateForm();">
                <div class="row half">
                    <div class="12u">
                        <input class="text" type="text" name="departmentName" placeholder="Department Name" value="${sessionScope.department.name}"/>
                        <span><c:out value="${sessionScope.errors['departmentName']}"/></span>
                    </div>
                </div>
                <button type="submit" form="department-form" style = "margin-top: 2%">Add</button>
                <span><c:out value="${sessionScope.info}"/></span>
            </form>
            	<c:remove var="info" scope="session"/>
            	<c:remove var="errors" scope="session"/>
            	<c:remove var="department" scope="session"/>
        </section>
    </div>
</div>

</body>
</html>
