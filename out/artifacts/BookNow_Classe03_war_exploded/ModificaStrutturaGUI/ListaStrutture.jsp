<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Le mie strutture</title>
    <link rel="stylesheet" type="text/css" href="ListaStrutture.css">
</head>
<body>
<h2 id="titoloStrutture">Le mie strutture</h2>
<!-- Lista strutture dell'albergatore -->
<form>
<c:forEach var="struttura" items="${utente.strutture}">
    <table class="strutture">
        <tr>
            <td>
                <p>${struttura.nome}</p>
                <p>${struttura.indirizzo}</p>
                <p>${struttura.stanze.size}</p>
            </td>
            <td>
                <button onclick="location.href='modifica-struttura?id=${struttura.ID_Struttura}'">Modifica Struttura</button>
            </td>
        </tr>
    </table>
</c:forEach>
</form>

</body>
</html>
