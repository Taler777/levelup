<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="tasks" scope="request" type="java.util.List<ru.levelup.junior.entities.Task>"></jsp:useBean>
<html>
<head>
    <title>
        Tasks list
    </title>
</head>
<body>

<h1>Tasks list</h1>

<table>

    <thead>
    <tr>
        <th>name</th>
        <th>text</th>
        <th>report</th>
        <th>rating</th>
        <th>state</th>
        <th>author</th>
        <th>executor</th>
        <th>date open</th>
        <th>date close</th>
    </tr>
    </thead>

    <tbody>

    <c:forEach items="${tasks}" var="task">
        <tr>
            <td>${task.name}</td>
            <td>${task.text}</td>
            <td>${task.report}</td>
            <td>${task.rating}</td>
            <td>${task.state}</td>
            <td>${task.author}</td>
            <td>
                <c:if test="${not empty task.executor}"><c:out value="${task.executor}"/></c:if>
                <c:if test="${empty task.executor}"><c:out value="---"/></c:if>
            </td>
            <td>${task.dateOpen}</td>
            <td>${task.dateClose}</td>
        </tr>
    </c:forEach>

    </tbody>

</table>

<c:if test="${not empty sessionScope['userId']}">
    <p><a href="/">Main page</a></p>
    <p><a href="/users">Users</a></p>
</c:if>

</body>
</html>