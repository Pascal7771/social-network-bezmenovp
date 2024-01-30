<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<c:choose>
    <c:when test="${error==1}">
        <p style="color: red">Invalid login or password</p>
    </c:when>
    <c:otherwise>
        <p>Login</p>
    </c:otherwise>
</c:choose>
<form action="login" method="post">
    <br> email<input type="text" required style=position:absolute;left:7% name="accountEmail" value="${accountEmail}">
    <br> password<input type="text" required style=position:absolute;left:7% name="accountPassword" value="${accountPassword}">
    <br>
    <br><input type="submit"> login</input>
    <br>
    <input type="checkbox" name="remember" checked="checked">
    <br>
    <a href="<c:url value="create-account"/>">Register</a>
</form>
</body>
</html>
