<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Редактирование группы</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
<div class="container mt-3">
    <h1>Редактирование группы</h1>
    <div class="row mt-3">
        <div class="col">
            <div class="d-flex justify-content-center">
                <form action="${pageContext.request.contextPath}/group/update" method="post"
                      enctype="multipart/form-data">
                    <input type="hidden" name="id" value="${groupRequest.id}"/>
                    <div class="form-group row text-center">
                        <b>Заполните поля для создания группы(* - обязательные)</b>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Название:<sup>*</sup></label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" required id="name" name="name"
                                   value="${groupRequest.name}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Описание:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="description" name="description"
                                   value="${groupRequest.description}">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2">Удалить аватар</label>
                        <div class="col-md-8">
                            <input type="checkbox" name="deleteAvatar">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-md-2">Фото:</label>
                        <div class="col-md-8">
                            <input type="file" class="form-control" name="img">
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
                <form action="${pageContext.request.contextPath}/group/delete" method="post">
                    <input type="hidden" name="id" value="${groupRequest.id}">
                    <input type="hidden" name="idAccount" value="${userId}">
                    <div class="form-group row">
                        <input type="submit" class="btn btn-primary btn-lg btn-block" id="deleteGroup"
                               value="Удалить группу"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    <%@include file="/WEB-INF/js/update-group.js"%>
</script>
</body>
</html>