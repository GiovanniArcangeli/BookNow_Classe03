<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Stanze Disponibili</title>
    <link rel="stylesheet" type="text/css" href="StanzeDisponibili.css">
</head>
<body>
<h2 id="stanzeDisponibili">Stanze disponibili</h2>
    <!-- Lista Stanze con Stanza.struttura = struttura && Stanza.isDisponibile(dataIn, dataOut) && Stanza.capienza > numOspiti -->
<c:forEach var="stanza" items="${stanzeDisponibili}">
    <table class="stanze">
        <tr>
            <td>
                <p>${stanza.getNumeroStanza()}</p>
                <p>${stanza.getCosto()}</p>
                <p>${stanza.getCapienza()}</p>
                <p>${stanza.getDescrizione()}</p>
                <p>${stanza.getServiziOfferti()}</p>
            </td>
            <td>
                <button>Prenota</button>
            </td>
        </tr>
    </table>
</c:forEach>
</body>
</html>
