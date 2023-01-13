<!DOCTYPE html>
<html>
<head>
    <title>Modifica Prenotazione</title>
    <link rel="stylesheet" type="text/css" href="FormModificaStruttura.css">
</head>
<body>
<p>Modifica i dati della prenotazione</p>
<div id="form">
    <form method="post" action="/modifica-struttura">
        <input type="hidden" id="ID_Struttura" name="ID_Struttura" value="${ID_Struttura}">
        <label for="nomeStruct">Nome della Struttura</label>
        <input id="nomeStruct" type="text" name="nomeStruct"><br>
        <label for="indirizzo">Indirizzo</label>
        <input id="indirizzo" type="text" name="indirizzo"><br>
        <input type="submit" value="Modifica">
    </form>
</div>
</body>
</html>
