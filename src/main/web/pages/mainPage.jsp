<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="login" scope="request" type="java.lang.String"></jsp:useBean>
<html>
<head>
    <title>
        Title
    </title>
    <style type="text/css">
        .error{
            color:red;
        }
    </style>
</head>
<body>
<h1>Hello</h1>

<c:if test="${empty sessionScope['userId']}">
<form method="post" action="/login">

    <p>Login: <input type="text" name="login" value="${login}"></p>

    <p>Password: <input type="password" name="password"></p>

    <p><input type="submit"></p>

</form>
</c:if>

<c:if test="${not empty sessionScope['userId']}">
<p><a href="/users">Users</a> </p>
<p><a href="/tasks">Tasks</a> </p>
</c:if>

<c:if test="${not empty login}">
    <p class="error">Login or password is incorrect</p>
</c:if>

</body>
</html>