<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="icon"  href="Media/Favicon.png">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="./CSS/Login.css">
    <link rel="icon" href="Media/Favicon.png">

</head>
<body>
<!-- Header -->
<header id="header">
    <a href="index.jsp"><img id="Logo" src="./Media/Logo.png" alt="Logo"></a>
</header>
<!-- Container Form -->
<div id="container">
    <h2>Login</h2>
    <!-- Test errore del login -->
    <c:if test="${sessionScope.loginError == true}">
        <h3 id="loginError"> Username e/o Password errati </h3>
    </c:if>
    <!-- Form di Login -->
    <form method="post" action="LoginSer" name="LoginForm">
        <label for="usernameLogin">Username</label><br>
        <input id="usernameLogin" type="text" name="usernameLogin"><br>
        <label for="passwordLogin">Password </label><br>
        <input id="passwordLogin" type="password" name="passwordLogin"><br>
        <input type="submit" value="Login">
    </form>
    <!-- Link alla Registrazione -->
    <p>Non sei ancora registrato?<a href="./registrazione.jsp">Registrati</a></p>
</div>
<jsp:include page="./footer.jsp" />
</body>
</html>