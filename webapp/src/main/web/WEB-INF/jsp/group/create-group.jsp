<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создать группу</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
<div class="container mt-3">
    <div class="row mt-3">
        <div class="col">
            <div class="d-flex justify-content-center">
                <form:form action="${pageContext.request.contextPath}/group/create" method="post"
                           enctype="multipart/form-data">
                    <div class="form-group row text-center">
                        <b>Заполните поля для создания группы(* - обязательные)</b>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Название:<sup>*</sup></label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" required id="name" name="name">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Описание:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="description" name="description">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Фото:</label>
                        <div class="col-sm-8">
                            <input type="file" class="form-control" name="img">
                        </div>
                    </div>
                    <input type="hidden" name="idCreator" value="${userId}">
                    <div class="form-group row">
                        <input type="submit" class="btn btn-primary btn-lg btn-block mt-2" value="Создать"/>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
