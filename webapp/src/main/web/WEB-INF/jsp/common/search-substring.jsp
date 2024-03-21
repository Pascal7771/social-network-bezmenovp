<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="/WEB-INF/jsp/common/header.jsp"/>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <title>Поиск</title>
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
<div id="left">
    <div class="container mt-3">
        <h1>Аккаунты</h1>
        <div class="row mt-3">
            <table class="table">
                <tbody>
                <c:forEach var="account" items="${accountList}">
                <tr>
                    <td>
                        <li>
                            <a href="<c:url value="/account/info?id=${account.id}" />">${account.name}, ${account.surname}</a>
                        </li>
                    </td>

                    </c:forEach>
                    <c:forEach var="number" begin="01" end="${accountCount}">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/search/substr/${number-1}?substr=${substr}"> ${number}</a>
                    </c:forEach>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div id="right">
    <div class="container mt-3">
        <h1>Группы</h1>
        <div class="row mt-3">
            <table class="table">
                <c:forEach var="group" items="${groupList}">
                <tr>
                    <td>
                        <li>
                            <a href="<c:url value="/group/info?id=${group.id}" />">${group.name}</a>
                        </li>
                    </td>
                    </c:forEach>
                    <c:forEach var="number" begin="01" end="${groupCount}">
                        <a class="page-link"
                           href="${pageContext.request.contextPath}/search/substr/${number-1}?substr=${substr}"> ${number}</a>
                    </c:forEach>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>