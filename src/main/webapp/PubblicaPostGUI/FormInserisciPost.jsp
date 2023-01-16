<!DOCTYPE >
<html>
<head>
    <title>Pubblica Post</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/PubblicaPostGUI/FormInserisciPost.css">
    <script src="${pageContext.request.contextPath}/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/PubblicaPostGUI/FormInserisciPost.js"></script>
</head>
<body>
<p id="links"><a href="${pageContext.request.contextPath}/GestisciPrenotazioneGUI/ProfiloCliente.jsp">Torna al profilo</a></p>
<div id="form">
    <form id="postForm" method="post" action="${pageContext.request.contextPath}/forum">
        <p>Scrivi il post</p>
        <label for="titolo">Titolo</label>
        <input id="titolo" type="text" name="titolo"><br>
        <label for="corpo">Testo</label><br>
        <textarea id="corpo" name="corpo" cols="60" rows="10" placeholder="Inserisci il post"></textarea>
        <br>
        <label for="tags">Tags</label>
        <input id="tags" type="text" name="tags"><br>
        <input type="button" value="Pubblica Post" onclick="formInserisciPost()">
    </form>
</div>
</body>
</html>
