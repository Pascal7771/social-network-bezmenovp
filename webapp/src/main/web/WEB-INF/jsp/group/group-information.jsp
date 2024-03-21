<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>${groupDto.name}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
<div class="container mt-1">
    <h1>Группа: "${groupDto.name}"</h1>
    <div class="row">
        <div class="col">
            <c:choose>
                <c:when test="${groupDto!=null}">
                    <img src="<c:url value="/group/avatar?id=${groupDto.id}"/>"
                         class="rounded mx-auto d-block" width="250px" height="200px" alt="photo">
                    <table class="table mt-1">
                        <tbody>
                        <tr>
                            <td>Описание: "${groupDto.description}"</td>
                        </tr>
                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath}/group/members?id=${groupDto.id}"
                                   class="btn btn-primary">Участники группы</a>
                            </td>
                        </tr>
                        <c:choose>
                            <c:when test="${groupDto.idCreator == userId}">
                                <tr>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/group/update?id=${groupDto.id}"
                                           class="btn btn-primary">Редактировать группу</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/group/members/invites?id=${groupDto.id}"
                                           class="btn btn-primary">Заявки на
                                            вступление</a>
                                    </td>
                                </tr>
                            </c:when>
                        </c:choose>
                        <c:choose>
                        <c:when test="${contains == true || groupDto.idCreator == userId}">
                            <tr>
                                <form:form action="${pageContext.request.contextPath}/group/members/leave"
                                           method="post">
                                    <input type="hidden" name="idGroup" value="${groupDto.id}">
                                    <input type="hidden" name="idMember" value="${userId}">
                                    <td><input type="submit" lass="btn btn-primary" id="leave"
                                               value="Покинуть группу"/></td>
                                </form:form>
                            </tr>
                        </c:when>
                        <c:otherwise>
                        <c:choose>
                        <c:when test="${isInvite == true}">
                        <td><br><input type="submit" class="btn btn-primary"
                                       value="Заявка отправлена"/>
                        <td>
                            </c:when>
                            <c:otherwise>
                            <tr>
                                <form:form action="${pageContext.request.contextPath}/group/members/invite"
                                           method="post">
                                    <input type="hidden" name="idGroup" value="${groupDto.id}">
                                    <input type="hidden" name="idMember" value="${userId}">
                                    <td><input type="submit" class="btn btn-primary" id="invite"
                                               value="Подать заявку на вступление"/></td>
                                </form:form>
                            </tr>
                            </c:otherwise>
                            </c:choose>
                            </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise><b>Группа не найдена</b></c:otherwise>
            </c:choose>
        </div>
        <c:choose>
            <c:when test="${contains == true || groupDto.idCreator == userId}">
                <div class="col-8 bg-light">
                    <div class="row mt-2">
                        <div class="col">
                            <form:form action="${pageContext.request.contextPath}/group/message/create" method="post"
                                       enctype="multipart/form-data">
                                <div class="form-row">
                                    <div class="form-group w-100">
                                        <input type="text" class="form-control" name="textMessage"
                                               placeholder="Сообщение">
                                    </div>
                                    <div class="form-group mt-2 col-md-9">
                                        <input type="file" name="img" class="file-input"/>
                                    </div>
                                    <input type="hidden" name="senderId" value="${userId}">
                                    <input type="hidden" name="recipientId" value="${groupDto.id}">
                                    <input type="hidden" name="appointment" value="wall">
                                    <div class="form-group mt-2 col-md-2">
                                        <button class="btn btn-primary" type="submit">Отправить</button>
                                    </div>
                                </div>
                            </form:form>
                            <div class="row scrollDiv">
                                <div class="col">
                                    <div id="accordion">
                                        <h3>Сообщения на стене</h3>
                                        <div>
                                            <div class="row">
                                                <c:forEach var="message" items="${messageWall}">
                                                    <div class="col">
                                                        <a class="text-decoration-none"
                                                           href="<c:url value="/account/info?id=${message.senderId}"/>">${message.accountSender.name} ${message.accountSender.surname}
                                                            <br>${message.timeSend}</a>
                                                    </div>
                                                    <div class="col-9">
                                                        <div class="alert alert-primary"
                                                             role="alert">${message.textMessage}
                                                            <br>
                                                            <c:choose>
                                                                <c:when test="${message.img!=null}">
                                                                    <img src="<c:url value="/group/message/img?id=${message.id}"/>"
                                                                         class="rounded mx-auto d-block mt-2"
                                                                         width="400px"
                                                                         height="400px" alt="photo">
                                                                </c:when>
                                                            </c:choose>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
        </c:choose>
    </div>
</div>
<script type="text/javascript">
    <%@include file="/WEB-INF/js/account-information.js"%>
</script>
</body>
</html>