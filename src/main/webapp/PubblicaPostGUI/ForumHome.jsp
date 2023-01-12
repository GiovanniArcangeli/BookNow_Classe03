<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Forum</title>
    <link rel="stylesheet" type="text/css" href="ForumHome.css">
</head>
<body>
<h2 id="forum">Forum</h2>
<p id="links"><a href="FormInserisciPost.jsp">Scrivi Post</a></p>
    <!-- Lista Post -->
<c:forEach var="post" items="${posts}">
    <div class="post">
        <p>${post.titolo}</p>
        <p>${post.corpo}</p>
        <p>${post.tags}</p>
        <p>${post.autore}</p>
    </div>
</c:forEach>
</body>
</html>
