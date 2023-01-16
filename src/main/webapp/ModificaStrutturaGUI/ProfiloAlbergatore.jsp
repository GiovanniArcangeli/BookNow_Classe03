<!DOCTYPE html>
<html>
<head>
    <title>Profilo</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ModificaStrutturaGUI/ProfiloAlbergatore.css">
</head>
<body>
<h3 id="profiloAlbergatore">Profilo Albergatore</h3>
<p id="links"><a href="${pageContext.request.contextPath}/ModificaStrutturaGUI/ListaStrutture.jsp">Le mie Strutture</a></p>
<button onclick="location.href='${pageContext.request.contextPath}/login'"> Logout </button>
<table id="dati">
    <tr>
        <td>Username ${utente.username}</td>
        <td>Partita IVA ${utente.p_iva}</td>
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
        <td>E-mail ${utente.email}</td>
        <td>Codice Fiscale ${utente.cf}</td>
    </tr>
</table>
</body>
</html>