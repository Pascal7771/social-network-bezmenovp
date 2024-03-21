<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ошибка</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
<br>
<div class="container mt-3">
    <div class="row mt-3">
        <h1>Упс...страницы не существует  </h1>
        <input type="button" class="btn btn-secondary" onclick="history.back();" value="Назад"/>
    </div>
</div>
</body>
</html>