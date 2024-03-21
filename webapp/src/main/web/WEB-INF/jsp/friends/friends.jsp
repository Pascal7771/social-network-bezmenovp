<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Друзья</title>
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
    <h1>Друзья</h1>
    <div class="row mt-3">
        <table class="table">
            <tbody>
            <c:forEach var="friend" items="${friends}">
                <tr>
                    <td>
                        <li><a href="<c:url value="/account/info?id=${friend.id}"/>">
                            <big>${friend.name} ${friend.surname}</big></a></li>
                    </td>
                    <form:form action="${pageContext.request.contextPath}/friends/delete" method="post">
                        <input type="hidden" name="userId" value="${userId}">
                        <input type="hidden" name="idFriend" value="${friend.id}">
                        <td><input type="submit" class="btn btn-primary" id="invite" value="Удалить"/></td>
                    </form:form>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>