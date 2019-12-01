<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<html>
<head>
    <title>
        Title
    </title>
    <style type="text/css">
        .error {
            color: red;
        }
    </style>
</head>
<body>
<h1>Main page</h1>

<c:if test="${empty sessionScope['userId']}">
    <form method="post" action="/login">

            <%--<p>Login: <input type="text" name="login" value="${login}"></p>--%>
        <p>Login: <input type="text" name="login" value="${param['login']}"></p>

        <p>Password: <input type="password" name="password"></p>

        <p><input type="submit"></p>

    </form>
    <p><a href="/register">Registration</a></p>

</c:if>

<c:if test="${not empty sessionScope['userId']}">
    <p><a href="/users">Users</a></p>
    <p><a href="/tasks">Tasks</a></p>
</c:if>

<c:if test="${not empty login}">
    <p class="error">Login or password is incorrect</p>
</c:if>

</body>
</html>