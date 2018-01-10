<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
    <meta name="viewport" charset="UTF-8" content="width=device-width, initial-scale=1.0">
    <title>ESIA</title>
</head>
<body>
    <h1>Hello!</h1>
    <form:form action="/esia-login" method="get">
        <input type="submit" value="LogIn to ESIA">
    </form:form>
</body>