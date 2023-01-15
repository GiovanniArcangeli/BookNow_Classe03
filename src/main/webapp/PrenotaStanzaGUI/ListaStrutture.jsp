<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Strutture Disponibili</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/PrenotaStanzaGUI/ListaStrutture.css">
</head>
<body>
<h2 id="struttureDisponibili">Strutture disponibili</h2>
<c:forEach var="struttura" items="${struttureDisponibili}">
    <table class="strutture">
        <tr>
            <td>
                <p>Nome</p>
                <p>Indirizzo</p>
                <p>Numero Stanze: </p>
            </td>
            <td>
                <button onclick="location.href='prenota/vedi-stanze?id=${struttura.ID_Struttura}'">Seleziona Struttura</button>
            </td>
        </tr>
    </table>
</c:forEach>
</body>
</html>
