<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Stanze Disponibili</title>
    <link rel="stylesheet" type="text/css" href="StanzeDisponibili.css">
</head>
<body>
<h2 id="stanzeDisponibili">Stanze disponibili</h2>

<c:forEach var="stanza" items="${stanzeDisponibili}">
    <table class="stanze">
        <tr>
            <td>
                <p>${stanza.numeroStanza}</p>
                <p>${stanza.costo}</p>
                <p>${stanza.capienza}</p>
                <p>${stanza.descrizione}</p>
                <p>${stanza.serviziOfferti}</p>
            </td>
            <td>
                <button onclick="location.href='prenota/prenota-stanza?id=${stanza.struttura.ID_Struttura}&num=${stanza.numeroStanza}'">Prenota</button>
            </td>
        </tr>
    </table>
</c:forEach>
</body>
</html>
