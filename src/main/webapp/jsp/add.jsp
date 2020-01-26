<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add user</title>
    <link rel="stylesheet" href="/css/add_page.css">
    <link href="https://fonts.googleapis.com/css?family=Shadows+Into+Light&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Questrial&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Assistant&display=swap" rel="stylesheet">
</head>
<body>
<div class="conteiner">
    <h1 class="title">Add user</h1>
    <form action="/add" method="post">
        <table class="table_01">
            <tr>
                <td>Login:</td>
                <td>
                    <input  type="text" name="login" required="required"/>
                </td>
            </tr>
            <tr>
                <td>F. name:</td>
                <td>
                    <input  type="text" name="firstName" required="required"/>
                </td>
            </tr>
            <tr>
                <td>S. name:</td>
                <td>
                    <input  type="text" name="lastName" required="required"/>
                </td>
            </tr>
            <tr>
                <td>Password:</td>
                <td>
                    <input  type="password" name="password" required="required"/>
                </td>
            </tr>
            <tr>
                <td>Birthday:</td>
                <td>
                    <input type="text" name="birthday" placeholder="00.00.0000"
                           pattern="([0-2]{1}[0-9]{1}|[3]{1}[01]{1}).([0]{1}[1-9]{1}|[1]{1}[12]{1}).[12]{1}[0-9]{1}[0-9]{1}[0-9]{1}"
                           required="required">
                </td>
            </tr>
        </table>
        <input class="btn2" type="submit" value="Add">
    </form>
    <h1 class="author">Created by Artem Lambert</h1>
</div>
</body>
</html>