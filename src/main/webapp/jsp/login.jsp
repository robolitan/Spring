<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--    <link href="assets/css/style.css" rel="stylesheet">-->
    <title>Login in</title>
</head>
<body>
<div class="conteiner">
    <form action="/login" method="post">
        <table class ="table_01">
            <tr>
                <td>Login:</td>
                <td>
                    <input  type="text" name="username" required="required"/>
                </td>
            </tr>
            <tr>
                <td>Password:</td>
                <td>
                    <input  type="password" name="password" required="required"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Ok">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
