<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Участники</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
<div class="container mt-3">
    <h1>Участники</h1>
    <div class="row mt-3">
        <table class="table">
            <tbody>
            <c:forEach var="member" items="${membersGroup}">
                <tr>
                    <td>
                        <li>
                            <a href="<c:url value="/account/info?id=${member.id}" />">${member.name}, ${member.surname}</a>
                        </li>
                    </td>
                    <c:choose>
                        <c:when test="${group.idCreator == userId}">
                            <form:form action="${pageContext.request.contextPath}/group/members/delete" method="post">
                                <input type="hidden" name="idGroup" value="${group.id}">
                                <input type="hidden" name="idMember" value="${member.id}">
                                <c:choose>
                                    <c:when test="${group.idCreator != member.id}">
                                        <td><input type="submit" class="btn btn-secondary" id="delete"
                                                   value="Исключить"/>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><strong>Администратор</strong></td>
                                    </c:otherwise>
                                </c:choose>
                            </form:form>
                        </c:when>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>