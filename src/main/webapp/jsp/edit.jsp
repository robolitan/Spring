<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit user</title>
    <link rel="stylesheet" href="/css/edit_page.css">
    <link href="https://fonts.googleapis.com/css?family=Shadows+Into+Light&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Questrial&display=swap" rel="stylesheet">
</head>
<body>
<div class="conteiner">
    <h1 class="title">Edit user</h1>
    <form  action="/admin/edit" method="post">
        <table class="table_01">
            <tr>
                <th>Login</th>
                <th>F. name</th>
                <th>L. name</th>
                <th>Birthday</th>
                <th>admin</th>
            </tr>
            <tr>
                <td>
                    <input type="hidden" value="${user.id}" name="id">
                    <input type="text" value="${user.login}" name="login" required="required">
                </td>
                <td>
                    <input type="text" value="${user.firstName}" name="firstName" required="required">
                </td>
                <td>
                    <input type="text" value="${user.lastName}" name="lastName" required="required">
                </td>
                <td>
                    <input type="text" value="${user.birthday}" name="birthday"
                           pattern="([0-2]{1}[0-9]{1}|[3]{1}[01]{1}).([0]{1}[1-9]{1}|[1]{1}[012]{1}).[12]{1}[0-9]{1}[0-9]{1}[0-9]{1}"
                           placeholder="00.00.0000" required="required">
                </td>
                <td>
                    <input type="checkbox" id = "checkbox" name="isAdmin" value="${admin}">
                    <script type="text/javascript">
                           var chkBox = document.getElementById("checkbox")
                           if(chkBox.value == "true"){
                               chkBox.checked = true
                           }else {
                               chkBox.checked = false;
                           }
                    </script>
                </td>
            </tr>
        </table>
        <input type="submit" value="Edite" class="btn2">
        <input type="button" value="Cancel" onclick="history.back()" class="btn2">
    </form>
    <h1 class="author">Created by Artem Lambert</h1>
</div>
</body>
</html>