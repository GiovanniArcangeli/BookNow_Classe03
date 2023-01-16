<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Forum</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/PubblicaPostGUI/ForumHome.css">
</head>
<body>
<h2 id="forum">Forum</h2>
<p id="links"><a href="${pageContext.request.contextPath}/PubblicaPostGUI/FormInserisciPost.jsp">Scrivi Post</a></p>
    <!-- Lista Post -->
<c:forEach var="post" items="${posts}">
    <div class="post">
        <p>${post.titolo}</p>
        <p>${post.testo}</p>
        <p>${post.tags}</p>
        <p>${post.autore.username}</p>
    </div>
</c:forEach>
</body>
</html>
