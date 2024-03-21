<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактировать аккаунт</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
<div class="container mt-3">
    <div class="row mt-3">
        <div class="col">
            <div class="d-flex justify-content-center">
                <form action="${pageContext.request.contextPath}/account/update" method="post"
                      enctype="multipart/form-data">
                    <div class="form-group row text-center">
                        <b>Редактирование аккаунта</b>
                        <input type="hidden" name="id" value="${account.id}">
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2">Фамилия:<sup>*</sup></label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" required name="surname" value="${account.surname}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2">Имя:<sup>*</sup></label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" required name="name" value="${account.name}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2">Отчество:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="patronymic"
                                   value="${account.patronymic}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2">Email:<sup>*</sup></label>
                        <div class="col-md-8">
                            <input type="email" class="form-control" required name="email" value="${account.email}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2">Пароль:<sup>*</sup></label>
                        <div class="col-md-8">
                            <input type="password" class="form-control" required name="password"
                                   value="${account.password}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2">Дата рождения:<sup>*</sup></label>
                        <div class="col-md-8">
                            <input type="text" required class="form-control" name="birthDay"
                                   placeholder="В формате (ГГГГ-ММ-ДД)" value="${account.birthDay}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2">Телефон:<sup>*</sup></label>
                        <div class="col-md-4">
                            <input type="text" required class="form-control" id="phone" name="phone"
                                   placeholder="Без +7" value="${account.phone}">
                        </div>
                        <div class="col-md-2">
                            <input type="button" class="btn btn-info" id="testPhone" value="Добавить"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2">Рабочий тел.:</label>
                        <div class="col-md-4">
                            <input type="text" class="form-control" id="workPhone" name="workPhone"
                                   placeholder="Без +7" value="${account.workPhone}">
                        </div>
                        <div class="col-md-2">
                            <input type="button" class="btn btn-info" id="testWorkPhone" value="Добавить"/>
                        </div>
                        <div class="col-md-2">
                            <input type="button" class="btn btn-info" id="delWorkPhone" value="Удалить"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2">Адрес:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" required name="address" value="${account.address}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2">Рабочий адр.:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="workAddress"
                                   value="${account.workAddress}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2">ICQ:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="icq" value="${account.icq}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2">Skype:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="skype" value="${account.skype}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2">О себе:</label>
                        <div class="col-md-8">
                            <input type="text" class="form-control" name="additionally"
                                   value="${account.additionally}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2">Фото:</label>
                        <div class="col-md-8">
                            <input type="file" class="form-control" name="img">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2">Удалить фото</label>
                        <div class="col-md-8">
                            <input type="checkbox" name="deleteAvatar">
                        </div>
                    </div>
                    <div class="form-group row">
                        <input type="submit" class="btn btn-primary btn-lg btn-block" id="saveChange"
                               value="Сохранить"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="row mt-3">
        <div class="col">
            <div class="d-flex justify-content-center">
                <c:choose>
                    <c:when test="${account.role == 'ADMIN'}">
                        <tr>
                            <form:form action="${pageContext.request.contextPath}/account/update-form-file"
                                       method="post" enctype="multipart/form-data">
                                <input type="hidden" name="id" value="${account.id}">
                                <div class="form-group row">
                                    <label class="control-label col-md-4">Редактировать из файла</label>
                                    <div class="col-md-6">
                                        <input type="file" class="form-control" name="file">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <input type="submit" class="btn btn-primary btn-lg"
                                           value="Редактировать из файла"/>
                                </div>
                            </form:form>
                        </tr>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    <%@include file="/WEB-INF/js/update-account.js"%>
</script>
</body>
</html>