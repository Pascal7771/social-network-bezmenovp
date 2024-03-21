<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Ваши диалоги</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
<div class="container mt-3">
    <div class="row">
        <c:choose>
            <c:when test="${dialogs.size()>0}">
                <div class="col">
                    <div class="list-group">
                        <c:forEach var="dialogue" items="${dialogs}">
                            <c:choose>
                                <c:when test="${user.id < dialogue.id}">
                                    <a href="${pageContext.request.contextPath}/account/message/personal/${user.id}&${dialogue.id}"
                                       class="list-group-item list-group-item-action">${dialogue.name} ${dialogue.surname}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/account/message/personal/${dialogue.id}&${user.id}"
                                       class="list-group-item list-group-item-action">${dialogue.name} ${dialogue.surname}</a>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                У вас пока нет сообщений
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${interlocutor.id != null}">
                <div class="col-8 bg-light">
                    <div class="row mt-2">
                        <div class="col">
                            <div class="form-group mt-2 col-md-2">
                                <a href="${pageContext.request.contextPath}/account/message/personal/${user.id}&${user.id}">
                                    <button class="btn btn-default">Назад к диалогам</button>
                                </a>
                            </div>
                            <div class="form-row">
                                <div class="form-group w-100">
                                    <input type="text" class="form-control" id="textMessage" placeholder="Сообщение">
                                </div>
                                <div class="form-group mt-2 col-md-2">
                                    <button class="btn btn-primary" onclick="sendMessage()">Отправить</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row scrollDiv mt-2">
                        <div class="col" id="message_template">
                            <c:forEach var="message" items="${messagePersonal}">
                                <c:choose>
                                    <c:when test="${message.senderId != user.id}">
                                        <div class="row">
                                            <div class="col">${message.timeSend}
                                                <a href="${pageContext.request.contextPath}/account/info?id=${message.senderId}"
                                                   class="text-decoration-none">${interlocutor.name} ${interlocutor.surname}</a>
                                            </div>
                                            <div class="col-9">
                                                <div class="alert alert-primary"
                                                     role="alert">${message.textMessage}</div>
                                            </div>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="row">
                                            <div class="col-9">
                                                <div class="alert alert-primary"
                                                     role="alert">${message.textMessage}</div>
                                            </div>
                                            <div class="col">${message.timeSend}
                                                <a href="${pageContext.request.contextPath}/account/info?id=${user.id}"
                                                   class="text-decoration-none">${user.name} ${user.surname}</a>
                                            </div>
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </c:when>
        </c:choose>
    </div>
</div>
<script src="${pageContext.request.contextPath }/WEB-INF/js/jquery-3.5.1.js"></script>
<script src="${pageContext.request.contextPath }/WEB-INF/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath }/WEB-INF/js/sockjs.js"></script>
<script src="${pageContext.request.contextPath }/WEB-INF/js/stomp.js"></script>
<script type="text/javascript">
    <%@include file="/WEB-INF/js/ws.js"%>
</script>
<script>
    let url = '${pageContext.request.contextPath}';
    let senderId = ${user.id};
    let recipientId = ${interlocutor.id};
    let sender = '${user.name}' + ' ' + '${user.surname}';
    let recipient = '${interlocutor.name}' + ' ' + '${interlocutor.surname}';
    $(function () {
        connect();
    });
</script>
</body>
</html>