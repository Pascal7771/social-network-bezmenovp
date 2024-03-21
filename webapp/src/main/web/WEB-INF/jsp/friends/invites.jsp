<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заявки в друзья</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <style type="text/css">
        * {
            margin: 0;
            padding: 0;
        }

        p {
            padding: 30px;
        }

        #left {
            position: absolute;
            left: 0;
            top: 20%;
            width: 50%;
        }

        #right {
            position: absolute;
            right: 0;
            top: 20%;
            width: 50%;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
<div id="left">
    <div class="container mt-3">
        <h1>Заявки</h1>
        <div class="row mt-3">
            <table class="table">
                <tbody>
                <c:choose>
                    <c:when test="${invites.size()>0}">
                        <c:forEach var="invite" items="${invites}">
                            <tr>
                                <td>
                                    <li>
                                        <a href="<c:url value="/account/info?id=${invite.id}" />">${invite.name}, ${invite.surname}</a>
                                    </li>
                                </td>
                                <form:form action="${pageContext.request.contextPath}/friends/invites/add"
                                           method="post">
                                    <input type="hidden" name="userId" value="${userId}">
                                    <input type="hidden" name="idInviter" value="${invite.id}">
                                    <td><input type="submit" class="btn btn-primary" id="accept" value="Принять"/>
                                    </td>
                                </form:form>
                                <form:form action="${pageContext.request.contextPath}/friends/invites/reject"
                                           method="post">
                                    <input type="hidden" name="userId" value="${userId}">
                                    <input type="hidden" name="idInviter" value="${invite.id}">
                                    <td><input type="submit" class="btn btn-secondary" id="reject" value="Отклонить"/>
                                    </td>
                                </form:form>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise><big>Заявок нет</big></c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div id="right">
    <div class="container mt-3">
        <h1>Исходящие заявки </h1>
        <div class="row mt-3">
            <table class="table">
                <tbody>
                <c:choose>
                    <c:when test="${outInvites.size()>0}">
                        <c:forEach var="outInvite" items="${outInvites}">
                            <tr>
                                <td>
                                    <li>
                                        <a href="<c:url value="/account/info?id=${outInvite.id}" />">${outInvite.name}, ${outInvite.surname}</a>
                                    </li>
                                </td>
                                <form:form action="${pageContext.request.contextPath}/friends/invites/cancel"
                                           method="post">
                                    <input type="hidden" name="userId" value="${outInvite.id}">
                                    <input type="hidden" name="idInviter" value="${userId}">
                                    <td><input type="submit" class="btn btn-secondary" id="invite"
                                               value="Отменить заявку"/></td>
                                </form:form>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise><big>Иходящих заявок нет</big></c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>