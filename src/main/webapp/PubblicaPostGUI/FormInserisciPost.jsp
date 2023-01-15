<!DOCTYPE >
<html>
<head>
    <title>Pubblica Post</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/PubblicaPostGUI/FormInserisciPost.css">
</head>
<body>
<p>Scrivi il post</p>
<div id="form">
    <form method="post" action="${pageContext.request.contextPath}/forum">
        <label for="titolo">Titolo</label>
        <input id="titolo" type="text" name="titolo"><br>
        <label for="corpo">Testo</label><br>
        <textarea id="corpo" name="corpo" cols="60" rows="10" placeholder="Inserisci il post"></textarea>
        <br>
        <label for="tags">Tags</label>
        <input id="tags" type="text" name="tags"><br>
        <input type="submit" value="Pubblica Post">
    </form>
</div>
</body>
</html>
