<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Le mie prenotazioni</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/GestisciPrenotazioneGUI/Prenotazioni.css">
</head>
<body>
<h2 id="titoloPrenotazioni">Le mie prenotazioni</h2>
<!-- Lista prenotazioni del cliente -->
<c:forEach var="preno" items="${utente.prenotazioni}">
<table class="prenotazione">
    <tr>
        <td>
            <p>Prenotazione #${preno.id}</p>
            <p>${preno.dataIn} - ${preno.dataOut}</p>
            <p>Numero ospiti: ${preno.numOspiti}</p>
        </td>
        <td>
            <p>${preno.stanza.struttura}</p>
            <p>${preno.stanza.struttura.indirizzo}</p>
            <p>Stanza #${preno.stanza.numeroStanza}</p>
        </td>
        <td>
            <button onclick="location.href='gestisci-prenotazione?id=${preno.id}'">Modifica Prenotazione</button>
        </td>
    </tr>
</table>
</c:forEach>
</body>
</html>
