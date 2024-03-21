<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/login"/>"> <b>S_network</b></a>
        </div>
        <div class="form-inline">
            <div class="navbar">Пользователь:
                <a href="${pageContext.request.contextPath}/account/info?id=${userId}"
                   class="text-decoration-none">${userName}</a>
            </div>
            <div class="navbar-header">
                <form class="form-inline" action="${pageContext.request.contextPath}/search/substr/0">
                    <label>
                        <input class="form-control mr-sm-2" id="substr" name="substr">
                    </label>
                    <button class="btn btn-primary my-2 my-sm-0" type="submit">Поиск</button>
                </form>
            </div>
            <div class="navbar-header">
                <form lass="form-inline" action="${pageContext.request.contextPath}/logout">
                    <button class="btn btn-primary my-2 my-sm-0 right-arrow" type="submit">Выйти</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<script>
    type = "text/javascript" >
    <%@include file="/WEB-INF/js/search.js"%>
</script>
</body>
</html>