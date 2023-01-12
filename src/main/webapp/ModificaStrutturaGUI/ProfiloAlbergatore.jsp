<!DOCTYPE html>
<html>
<head>
    <title>Profilo</title>
    <link rel="stylesheet" type="text/css" href="ProfiloAlbergatore.css">
</head>
<body>
<h3 id="profiloAlbergatore">Profilo Albergatore</h3>
<p id="links"><a href="ListaStrutture.jsp">Le mie Strutture</a></p>
<table id="dati">
    <tr>
        <td>Username ${utente.getUsername()}</td>
        <td>Partita IVA ${utente.getP_iva()}</td>
    </tr>
    <tr>
        <td>Nome ${utente.getNome()}</td>
        <td>Cognome ${utente.getCognome()}</td>
    </tr>
    <tr>
        <td>Recapito Telefonico ${utente.getP_iva()}</td>
        <td>Data di nascita ${utente.getDataNascita()}</td>
    </tr>
    <tr>
        <td>E-mail ${utente.getEmail()}</td>
        <td>Codice Fiscale ${utente.getCF()}</td>
    </tr>
</table>
</body>
</html>