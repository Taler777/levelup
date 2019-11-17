<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="users" scope="request" type="java.util.List<ru.levelup.junior.entities.User>"></jsp:useBean>
<html>
<head>
    <title>
        Users list
    </title>
</head>
<body>

<h1>Users list</h1>

<table>

    <thead>
    <tr>
        <th> login</th>
        <th> rating</th>
    </tr>
    </thead>

    <tbody>

    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.login}</td>
            <td>${user.rating}</td>
        </tr>
    </c:forEach>

    </tbody>

</table>

<c:if test="${not empty sessionScope['userId']}">
    <p><a href="/">Main page</a></p>
    <p><a href="/tasks">Tasks</a></p>
</c:if>

</body>
</html>