<!DOCTYPE html>
<html>
<head>
    <title>Profilo</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/GestisciPrenotazioneGUI/ProfiloCliente.css">
</head>
<body>
<h2 id="profiloCliente">Profilo Cliente</h2>
<p id="links"><a href="${pageContext.request.contextPath}/GestisciPrenotazioneGUI/Prenotazioni.jsp">Le mie Prenotazioni</a> | <a href="${pageContext.request.contextPath}/PrenotaStanzaGUI/FormCercaStruttura.jsp">Effettua Prenotazione</a> | <a href="${pageContext.request.contextPath}/forum">Forum</a></p>
<button onclick="location.href='${pageContext.request.contextPath}/login'"> Logout </button>
<table id="dati">
    <tr>
        <td>Username ${utente.username}</td>
        <td>E-mail ${utente.email}</td>
    </tr>
    <tr>
        <td>Nome ${utente.nome}</td>
        <td>Cognome ${utente.cognome}</td>
    </tr>
    <tr>
        <td>Recapito Telefonico ${utente.recapitoTelefonico}</td>
        <td>Data di nascita ${utente.dataNascita}</td>
    </tr>
    <tr>
        <td>Codice Fiscale ${utente.cf}</td>
        <td>Numero Carta ${utente.numero_carta}</td>
    </tr>
    <tr>
        <td>Scadenza Carta ${utente.scadenza_carta}</td>
        <td>CVV ${utente.cvv}</td>
    </tr>
</table>
</body>
</html>
