<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Профиль</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
<c:choose>
<c:when test="${account!=null}">
<div class="container mt-1">
    <div class="row">
        <div class="col">
            <img src="<c:url value="/account/avatar?id=${account.id}"/>"
                 class="rounded mx-auto d-block" width="250px" height="200px" alt="photo">
            <table class="table mt-1">
                <tbody>
                <tr>
                    <td>День рождения: ${account.birthDay}</td>
                </tr>
                <tr>
                    <td>Телефон: ${account.phone}</td>
                </tr>
                <tr>
                    <td>Рабочий тел.: ${account.workPhone}</td>
                </tr>
                <tr>
                    <td>Адрес: ${account.address}</td>
                </tr>
                <tr>
                    <td>Рабочий адр.: ${account.workAddress}</td>
                </tr>
                <tr>
                    <td>Почта: ${account.email}</td>
                </tr>
                <tr>
                    <td>icq: ${account.icq}</td>
                </tr>
                <tr>
                    <td>Скайп: ${account.skype}</td>
                </tr>
                <tr>
                    <td>О себе: ${account.additionally}</td>
                </tr>
                <c:choose>
                    <c:when test="${account.id == userId}">
                        <tr>
                            <form:form action="${pageContext.request.contextPath}/account/update-form"
                                       method="post">
                                <input type="hidden" name="id" value="${account.id}">
                                <td><input type="submit" class="btn btn-primary" value="Редактировать аккаунт"/>
                                </td>
                            </form:form>
                        </tr>
                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath}/account/message/personal/${account.id}&${account.id}"
                                   class="btn btn-primary">Личные сообщения</a>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath}/friends?id=${account.id}"
                                   class="btn btn-primary">Мои друзья</a>
                            </td>
                        </tr>
                        <tr>
                            <form:form action="${pageContext.request.contextPath}/account/groups"
                                       method="post">
                                <input type="hidden" name="id" value="${account.id}">
                                <td><input type="submit" class="btn btn-primary" value="Мои группы"/></td>
                            </form:form>
                        </tr>
                        <tr>
                            <td>
                                <a href="${pageContext.request.contextPath}/group/create"
                                   class="btn btn-primary">Создать группу</a>
                            </td>
                        </tr>
                        <tr>
                            <form:form action="${pageContext.request.contextPath}/friends/invites"
                                       method="get">
                                <input type="hidden" name="id" value="${account.id}">
                                <td><input type="submit" class="btn btn-primary" value="Заявки в друзья"/></td>
                            </form:form>
                        </tr>
                        <tr>
                            <form:form action="${pageContext.request.contextPath}/group/members/outgoinginvites"
                                       method="post">
                                <input type="hidden" name="id" value="${account.id}">
                                <td><input type="submit" class="btn btn-primary" value="Заявки в группы"/></td>
                            </form:form>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${contains==false}">

                                <c:choose>
                                    <c:when test="${isInvite==false}">
                                        <tr><form:form action="${pageContext.request.contextPath}/friends/invite"
                                                       method="post">
                                            <input type="hidden" name="userId" value="${userId}">
                                            <input type="hidden" name="idFriend" value="${account.id}">
                                            <td><br><input type="submit" class="btn btn-primary" id="invite"
                                                           value="Добавить в друзья"/>
                                            <td>
                                        </form:form></tr>
                                    </c:when>
                                    <c:otherwise>
                                        <td><br><input type="submit" class="btn btn-primary"
                                                       value="Заявка отправлена"/>
                                        <td>
                                    </c:otherwise>
                                </c:choose>

                            </c:when>
                            <c:otherwise>
                                <tr>
                                <td>
                                    <a href="<c:url value="/account/message/create-personal?id=${account.id}&appointment=personal"/>"
                                       class="btn btn-primary">Написать
                                        личное сообщение</a></td>
                                </tr>
                                <tr><form:form action="${pageContext.request.contextPath}/friends/delete"
                                               method="post">
                                    <input type="hidden" name="userId" value="${userId}">
                                    <input type="hidden" name="idFriend" value="${account.id}">
                                    <td><input type="submit" class="btn btn-primary" id="delete"
                                               value="Удалить из друзей"/></td>
                                </form:form>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${isAdminUser == true && isAdminAccount == false}">
                        <tr>
                            <form:form action="${pageContext.request.contextPath}/account/make-admin/${account.id}"
                                       method="post">
                                <input type="hidden" name="id" value="${account.id}">
                                <td><input type="submit" class="btn btn-primary" value="Сделать администратором"/>
                                </td>
                            </form:form>
                        </tr>
                    </c:when>
                </c:choose>
                </tbody>
            </table>
        </div>
        <div class="col-8 bg-light">
            <div class="row mt-2">
                <div class="col">
                    <h1>${account.name} ${account.surname} ${account.patronymic}</h1>
                    <br>
                    <form:form action="${pageContext.request.contextPath}/account/message/create" method="post"
                               enctype="multipart/form-data">
                        <div class="form-row">
                            <div class="form-group w-100">
                                <input type="text" class="form-control" name="textMessage"
                                       placeholder="Сообщение">
                            </div>
                            <div class="form-group mt-2 col-md-9">
                                <input type="file" name="img" class="file-input"/>
                            </div>
                            <input type="hidden" name="recipientId" value="${account.id}">
                            <input type="hidden" name="senderId" value="${userId}">
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
                                                <div class="alert alert-primary" role="alert">${message.textMessage}
                                                    <br>
                                                    <c:choose>
                                                        <c:when test="${message.img!=null}">
                                                            <img src="<c:url value="/account/message/img?id=${message.id}"/>"
                                                                 class="rounded mx-auto d-block mt-2" width="400px"
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
    </div>
    </c:when>
    <c:otherwise><b>Аккаунт не найден</b></c:otherwise>
    </c:choose>
    <script type="text/javascript">
        <%@include file="/WEB-INF/js/account-information.js"%>
    </script>
</body>
</html>