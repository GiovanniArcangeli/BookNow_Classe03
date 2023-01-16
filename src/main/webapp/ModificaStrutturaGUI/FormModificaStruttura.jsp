<!DOCTYPE html>
<html>
<head>
    <title>Modifica Prenotazione</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ModificaStrutturaGUI/FormModificaStruttura.css">
    <script src="${pageContext.request.contextPath}/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/ModificaStrutturaGUI/FormModificaStruttura.js"></script>
</head>
<body>
<p>Modifica i dati della struttura</p>
<div id="form">
    <form id="strutturaForm" method="post" action="${pageContext.request.contextPath}/modifica-struttura">
        <input type="hidden" id="ID_Struttura" name="ID_Struttura" value="${struttura.ID_Struttura}">
        <label for="nomeStruct">Nome della Struttura</label>
        <input id="nomeStruct" type="text" name="nomeStruct" value="${struttura.nome}"><br>
        <label for="indirizzo">Indirizzo</label>
        <input id="indirizzo" type="text" name="indirizzo" value="${struttura.indirizzo}"><br>
        <input type="button" value="Modifica" onclick="formModificaStruttura()">
    </form>
</div>
</body>
</html>
