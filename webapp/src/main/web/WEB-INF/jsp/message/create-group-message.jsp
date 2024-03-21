<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Сообщение</title>
</head>
<body>
<form:form action="${pageContext.request.contextPath}/group/message/create" method="post" enctype="multipart/form-data">
    <input type="hidden" name="senderId" value="${userId}">
    <input type="hidden" name="recipientId" value="${recipientId}">
    <input type="hidden" name="appointment" value="${appointment}">
    <br>Сообщение:<input type="text" style=position:absolute;left:17% name="textMessage">
    <br>Фото:<input type="file" style=position:absolute;left:17% name="img" />
    <br>
    <br><input type="submit" value="Отправить"/>
</form:form>
</body>
</html>