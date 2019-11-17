<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Task adding</title>
</head>
<body>
<form:form action="/addTask" method="post" modelAttribute="form">
    <p>
        Topic:
        <form:input type="text" path="name"/>
        <form:errors path="name" cssStyle="color: red"/>
    </p>
    <p>
        Text:
        <form:input type="text" path="text"/>
        <form:errors path="text" cssStyle="color: red"/>
    </p>

    <input type="submit" value="Save">
</form:form>

<c:if test="${not empty error}">
    <p style="color: red"> ${error}</p>
</c:if>
</body>
</html>
