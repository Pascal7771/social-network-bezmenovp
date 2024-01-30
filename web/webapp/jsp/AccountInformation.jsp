<%@ page import="java.util.Base64" %>
<%@ page import="com.getjavajob.training.bezmenovp.socialnetwork.domain.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Account information</title>
    <style> body {
        font-family: Arial, sans-serif;
        margin: 20px;
    }

    h1 {
        margin-bottom: 20px;
    }

    img {
        margin-bottom: 20px;
        width: 200px;
        height: 200px;
    }

    table {
        border-collapse: collapse;
        margin: 20px 0;
    }

    table td {
        padding: 5px 10px;
    }

    table tr:nth-child(even) {
        background-color: #f9f9f9;
    }
    </style>
</head>
<body>
<jsp:include page="/jsp/Header.jsp"/>
<h1>Account information</h1>
<c:choose> <c:when test="${account!=null}">

    <% Account account = (Account) request.getAttribute("account");
        byte[] photoBytes = account.getAccountImage();
        if (photoBytes != null && photoBytes.length > 0) {
            String base64Photo = Base64.getEncoder().encodeToString(photoBytes); %>
    <img src="data:image/jpeg;base64, <%= base64Photo %>" alt="Фотография">
    <% } else { %>
    <p>Фотография отсутствует</p>
    <% } %>

    <table>
        <tr>
            <td>id:</td>
            <td>${account.accountID}</td>
        </tr>
        <tr>
            <td>name:</td>
            <td>${account.accountName}</td>
        </tr>
        <tr>
            <td>surname:</td>
            <td>${account.accountSurname}</td>
        </tr>
        <tr>
            <td>patronymic:</td>
            <td>${account.accountPatronymic}</td>
        </tr>
        <tr>
            <td>birth day:</td>
            <td>${account.accountBirthDay}</td>
        </tr>
        <tr>
            <td>phone:</td>
            <td>${account.accountPhone}</td>
        </tr>
        <tr>
            <td>work phone:</td>
            <td>${account.accountWorkPhone}</td>
        </tr>
        <tr>
            <td>address:</td>
            <td>${account.accountHomeAddress}</td>
        </tr>
        <tr>
            <td>work address:</td>
            <td>${account.accountBusinessAddress}</td>
        </tr>
        <tr>
            <td>email:</td>
            <td>${account.accountEmail}</td>
        </tr>
        <tr>
            <td>icq:</td>
            <td>${account.accountICQ}</td>
        </tr>
        <tr>
            <td>skype:</td>
            <td>${account.accountSkype}</td>
        </tr>
        <tr>
            <td>about:</td>
            <td>${account.accountAdditionalInformation}</td>
        </tr>
    </table>
    <h1>Wall</h1></c:when> <c:otherwise><b>account not found</b></c:otherwise> </c:choose></body>
</html>