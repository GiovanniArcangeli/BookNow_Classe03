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
<c:forEach var="struttura" items="${utente.getStrutture()}">
    <table class="strutture">
        <tr>
            <td>
                <p>${struttura.getNome()}</p>
                <p>${struttura.getIndirizzo()}</p>
                <p>${struttura.getStanze().size}</p>
            </td>
            <td>
                <button onclick="location.href='modifica-struttura?id=${struttura.id}'">Modifica Struttura</button>
            </td>
        </tr>
    </table>
</c:forEach>
</form>

</body>
</html>
