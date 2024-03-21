<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Вход</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <div class="d-flex justify-content-center">
                <a class="navbar-brand" href="<c:url value="/login"/>"> <b>S_network</b>
                </a>
            </div>
        </div>
    </div>
</div>
</div>
<div class="container mt-3">
    <c:choose>
        <c:when test="${error==1}">
            <div class="row">
                <div class="col-md-4 text-center">
                    <p style="color: red"><strong>Неверный логин или пароль</strong></p>
                </div>
            </div>
        </c:when>
    </c:choose>
    <br>
    <div class="row mt-3">
        <div class="col">
            <div class="d-flex justify-content-center">
                <form:form action="login" method="post">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Email</label>
                        <div class="col-sm-9">
                            <input type="email" required class="form-control" name="email"
                                   placeholder="Введите email" value="${email}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Пароль</label>
                        <div class="col-sm-9">
                            <input type="password" required class="form-control" name="password"
                                   placeholder="Введите пароль"
                                   value="${password}">
                        </div>
                    </div>
                    <div class="d-flex justify-content-center">
                        <div class="custom-control custom-switch mt-1">
                            <input type="checkbox" class="custom-control-input" checked="checked"
                                   name="remember" id="remember">
                            <label class="custom-control-label" for="remember">Запомнить меня</label>
                        </div>
                    </div>
                    <div class="form-group row">
                        <input type="submit" class="btn btn-primary btn-lg btn-block mt-2" value="Войти"/>
                    </div>
                    <div class="d-flex justify-content-center">
                        <div class="custom-control custom-switch mt-1">
                            <a href="<c:url value="/account/create"/>"><strong>Зарегистрироваться</strong></a>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>