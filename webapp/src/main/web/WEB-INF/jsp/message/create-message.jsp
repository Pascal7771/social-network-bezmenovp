<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Сообщение</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<div class="container mt-3">
    <div class="row mt-3">
        <div class="col">
            <div class="d-flex justify-content-center">
                <form:form action="${pageContext.request.contextPath}/account/message/create" method="post"
                           enctype="multipart/form-data">
                    <input type="hidden" name="senderId" value="${userId}">
                    <input type="hidden" name="recipientId" value="${recipientId}">
                    <input type="hidden" name="appointment" value="${appointment}">
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Сообщение:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" required id="textMessage" name="textMessage">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-4 col-form-label">Фото:</label>
                        <div class="col-sm-8">
                            <input type="file" class="form-control" name="img">
                        </div>
                    </div>
                    <div class="form-group row">
                        <input type="submit" class="btn btn-primary btn-lg btn-block mt-2" value="Отправить"/>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
</html>