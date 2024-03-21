<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Исходящие заявки</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
<div class="container mt-3">
    <h1>Заявки в группу</h1>
    <div class="row mt-3">
        <table class="table">
            <tbody>
            <c:choose>
                <c:when test="${groups.size()>0}">
                    <c:forEach var="group" items="${groups}">
                        <tr>
                            <td>
                                <li>
                                    <a href="<c:url value="/group/info?id=${group.id}" />">${group.name}</a>
                                </li>
                            </td>
                            <form:form action="${pageContext.request.contextPath}/group/members/invites/cancel"
                                       method="post">
                                <input type="hidden" name="idGroup" value="${group.id}">
                                <input type="hidden" name="idAccount" value="${idAccount}">
                                <td><input type="submit" class="btn btn-secondary" id="cancel"
                                           value="Отменить заявку"/></td>
                            </form:form>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise><p>Заявок нет</p></c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>