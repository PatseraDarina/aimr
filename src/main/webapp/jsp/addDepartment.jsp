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
        <ul class="errorMessages">
            <span>
                <c:choose>
                    <c:when test = "${not empty userRegErrors}">
                          <br><c:out value="${userRegErrors['captcha']}"/></br>
                          <c:out value="${userRegErrors['userExist']}"/>
                    </c:when>
                </c:choose>
            </span>
        </ul>
            <header class="major">
                <h2>New department</h2>
            </header>

            <form name="department-form" id="register-form" action="department" method="post" onsubmit="return validateForm();">
                <div class="row half">
                    <div class="12u">
                        <input class="text" type="text" name="firstName" id="firstName" placeholder="Department Name" value="${sessionScope.user.firstName}"/>
                        <span id="fNameErr"><c:out value="${userRegErrors['firstName']}"/></span>
                    </div>
                </div>

                <div class="row half">
                    <div class="12u">
                        <ul class="actions">
                              <button type="submit" form="department-form" class="button alt" style = "margin-top: 2%">Add</button>
                        </ul>
                    </div>
                </div>
            </form>
            	<c:remove var="userBean" scope="session"/>
            	<c:remove var="userRegErrors" scope="session"/>
        </section>
    </div>
</div>

</body>
</html>
