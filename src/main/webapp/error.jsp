
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">

    <title>Error page</title>
</head>
<body>
<p><h1><strong>An error occured!</strong></h1></p>
<p><h4>Something is broken. Please, let us know what you were doing when this error occured.</h4></p>
<p><h4><c:out value="${errorMessage}"/></h4></p>
</body>
</html>
