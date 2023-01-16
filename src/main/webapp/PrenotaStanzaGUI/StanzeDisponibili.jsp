<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Stanze Disponibili</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/PrenotaStanzaGUI/StanzeDisponibili.css">
</head>
<body>
<h2 id="stanzeDisponibili">Stanze disponibili</h2>

<c:forEach var="stanza" items="${stanzeDisponibili}">
    <table class="stanze">
        <tr>
            <td>
                <p>Numero stanza: ${stanza.numeroStanza}</p>
                <p>Costo: ${stanza.costo}€</p>
                <p>Capienza: ${stanza.capienza}</p>
                <p>Descrizione: ${stanza.descrizione}</p>
                <p>Servizi: ${stanza.serviziOfferti}</p>
            </td>
            <td>
                <button onclick="location.href='${pageContext.request.contextPath}/prenota?path=prenota-stanza&id=${stanza.struttura.ID_Struttura}&num=${stanza.numeroStanza}'">Prenota</button>
            </td>
        </tr>
    </table>
</c:forEach>
</body>
</html>
