<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
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
<div class="container mt-3">
    <div class="row mt-3">
        <div class="col">
            <div class="d-flex justify-content-center">
                <form:form action="${pageContext.request.contextPath}/account/create" method="post"
                           enctype="multipart/form-data">
                    <div class="form-group row text-center">
                        <b>Заполните поля для регистрации(* - обязательные)</b>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Фамилия:<sup>*</sup></label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" required name="surname">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Имя:<sup>*</sup></label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" required name="name">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Отчество:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="patronymic">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Email:<sup>*</sup></label>
                        <div class="col-sm-8">
                            <input type="email" class="form-control" required name="email">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Пароль:<sup>*</sup></label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" required name="password">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Дата рождения:<sup>*</sup></label>
                        <div class="col-sm-8">
                            <input type="text" required class="form-control" name="birthDay"
                                   placeholder="В формате (ГГГГ-ММ-ДД)">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Телефон:<sup>*</sup></label>
                        <div class="col-sm-5">
                            <input type="text" required class="form-control" id="phone" name="phone"
                                   placeholder="Без +7">
                        </div>
                        <div class="col-md-2">
                            <input type="button" class="btn btn-primary" id="testPhone" value="Добавить"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Рабочий тел.:</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" id="workPhone" name="workPhone"
                                   placeholder="Без +7">
                        </div>
                        <div class="col-md-2">
                            <input type="button" class="btn btn-primary" id="testWorkPhone" value="Добавить"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Адрес:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="address">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Рабочий адр.:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="workAddress">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">ICQ:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="icq">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Skype:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="skype">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">О себе:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="additionally">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Фото:</label>
                        <div class="col-sm-8">
                            <input type="file" class="form-control" name="img">
                        </div>
                    </div>
                    <div class="form-group row">
                        <input type="submit" class="btn btn-primary btn-lg btn-block mt-2" value="Зарегистрироваться"/>
                    </div>
                    <input type="hidden" name="role" value="USER">
                </form:form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    <%@include file="/WEB-INF/js/update-account.js"%>
</script>
</body>
</html>