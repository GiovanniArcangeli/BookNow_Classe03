<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modifica Prenotazione</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/GestisciPrenotazioneGUI/FormModificaPrenotazione.css">
    <script src="${pageContext.request.contextPath}/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/GestisciPrenotazioneGUI/FormModificaPrenotazione.js"></script>
</head>
<body>
<p>Modifica i dati della prenotazione</p>
<div id="form">
    <form id="prenotazioneForm" method="post" action="${pageContext.request.contextPath}/gestisci-prenotazione">
        <c:if test="${retry == true}">
            <p>Stanza non disponibile nelle date selezionate</p>
        </c:if>
        <input type="hidden" id="id" name="id" value="${prenotazione.ID_Prenotazione}">

        <label for="dataIn">Data del Check-in </label>
        <input id="dataIn" type="text" name="dataIn" ><br>

        <label for="dataOut">Data del Check-out </label>
        <input id="dataOut" type="text" name="dataOut" ><br>

        <label for="numOspiti">Numero di Ospiti </label>
        <input id="numOspiti" type="text" name="numOspiti"><br>

        <c:if test="${freeUpdate == true}">
            <p>La modifica della prenotazione è gratuita</p>
        </c:if>

        <input type="button" value="Modifica Prenotazione" onclick="FormModificaPrenotazione()">
    </form>
</div>
</body>
</html>