<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="LoginPage.css">
</head>
<body>
<h2 id="login">Login</h2>
<div id="form">
    <form method="post" action="login">
        <c:if test="${loginError == true}">
            <p>Username e/o password errati</p>
        </c:if>
        <label for="usernameLogin">Username </label>
        <input id="usernameLogin" type="text" name="usernameLogin"><br>
        <label for="passwordLogin">Password </label>
        <input id="passwordLogin" type="password" name="passwordLogin"><br>
        <input type="submit" value="Login">
    </form>
</div>
</body>
</html>