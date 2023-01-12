<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Strutture Disponibili</title>
    <link rel="stylesheet" type="text/css" href="ListaStrutture.css">
</head>
<body>
<h2 id="struttureDisponibili">Strutture disponibili</h2>
<!-- Lista Strutture con !Struttura.isSoldOut(dataIn, dataOut) -->
<c:forEach var="struttura" items="${struttureDisponibili}">
    <table class="strutture">
        <tr>
            <td>
                <p>Nome</p>
                <p>Indirizzo</p>
                <p>Numero Stanze: </p>
            </td>
            <td>
                <button>Seleziona Struttura</button>
            </td>
        </tr>
    </table>
</c:forEach>
</body>
</html>
