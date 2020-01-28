<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All user</title>
    <link rel="stylesheet" href="/css/main_page.css">
    <link href="https://fonts.googleapis.com/css?family=Shadows+Into+Light&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Questrial&display=swap" rel="stylesheet">
</head>
<body>
<div class="conteiner">
    <h1 class="title">All users</h1>
    <table class="table_01">
        <tr>
            <th>id</th>
            <th>login</th>
            <th>f. name</th>
            <th>l. name</th>
            <th>birthday</th>
            <th>action</th>
        </tr>
        <c:forEach var="user" items="${usersList}">
        <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.birthday}</td>
            <td>
                <form action="/admin/delete/${user.id}" method="post">
                    <input class="btn" type="submit" value="Delete">
                </form>
                <form action="/admin/edit/${user.id}" method="get">
                    <input class="btn" type="submit" value="Edit">
                </form>
            </td>
        </tr>
        </c:forEach>
    </table>
    <form action="/admin/add" method="get">
        <input class="btn2" type="submit" value="Add">
    </form>
    <h1 class="author">Created by Artem Lambert</h1>
</div>
</body>
</html>