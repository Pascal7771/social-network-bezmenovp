<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>User:${accountName}</p>
<form action="get-substr" method="get">
    <input type="text" name="substr">
    <input type="submit" value="search" />
</form>
<form action="logout">
    <input type="submit" value="logout" />
</form>
</body>
</html>
