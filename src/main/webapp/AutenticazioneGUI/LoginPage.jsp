<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/AutenticazioneGUI/LoginPage.css">
    <script src="${pageContext.request.contextPath}/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/AutenticazioneGUI/LoginPage.js"></script>
</head>
<body>
<h2 id="login">Login</h2>
<div id="form">
    <form id="loginForm" method="post" action="../login">
        <c:if test="${loginError == true}">
            <p>Username e/o password errati</p>
        </c:if>
        <label for="username">Username </label>
        <input id="username" type="text" name="username"><br>
        <label for="password">Password </label>
        <input id="password" type="password" name="password"><br>
        <input type="button" value="Login" onclick="LoginPage()">
    </form>
</div>
</body>
</html>