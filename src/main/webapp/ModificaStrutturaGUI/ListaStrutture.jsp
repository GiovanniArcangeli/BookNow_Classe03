<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Le mie strutture</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/ModificaStrutturaGUI/ListaStrutture.css">
</head>
<body>
<h2 id="titoloStrutture">Le mie strutture</h2>
<p id="links"><a href="${pageContext.request.contextPath}/ModificaStrutturaGUI/ProfiloAlbergatore.jsp">Torna al profilo</a></p>
<!-- Lista strutture dell'albergatore -->
<form>
<c:forEach var="struttura" items="${utente.strutture}">
    <table class="strutture">
        <tr>
            <td>
                <p>${struttura.nome}</p>
                <p>${struttura.indirizzo}</p>
            </td>
            <td>
                <button type="button" onclick="location.href='${pageContext.request.contextPath}/modifica-struttura?id=${struttura.ID_Struttura}'">Modifica Struttura</button>
            </td>
        </tr>
    </table>
</c:forEach>
</form>
</body>
</html>
