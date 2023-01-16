<!DOCTYPE html>
<html>
<head>
    <title>Cerca Strutture</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/PrenotaStanzaGUI/FormCercaStruttura.css">
    <script src="../jquery.js"></script>
    <script src="./FormCercaStruttura.js"></script>
</head>
<body>
<p id="links"><a href="${pageContext.request.contextPath}/GestisciPrenotazioneGUI/ProfiloCliente.jsp">Torna al profilo</a></p>
<div id="form">
    <p>Inserisci i dati della tua prenotazione</p>
    <form id="prenotaStrutturaForm" action="${pageContext.request.contextPath}/prenota" method="post">

        <label for="dataIn">Data del Check-in</label>
        <input id="dataIn" type="text" name="dataIn"><br>

        <label for="dataOut">Data del Check-out </label>
        <input id="dataOut" type="text" name="dataOut"><br>

        <label for="numOspiti">Numero di Ospiti </label>
        <input id="numOspiti" type="text" name="numOspiti"><br>

        <input type="button" value="Cerca Strutture" onclick="FormCercaStruttura()">
    </form>
</div>
</body>
</html>
