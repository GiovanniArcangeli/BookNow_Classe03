<!DOCTYPE html>
<html>
<head>
    <title>Modifica Prenotazione</title>
    <link rel="stylesheet" type="text/css" href="FormModificaPrenotazione.css">
</head>
<body>
<p>Modifica i dati della prenotazione</p>
<div id="form">
    <form method="post" action="gestisci-prenotazione">
        <input type="hidden" id="id" name="id" value="${prenotazione.ID_Prenotazione}">
        <label for="dataIn">Data del Check-in </label>
        <input id="dataIn" type="date" name="dataIn">${prenotazione.dataIn}<br>
        <label for="dataOut">Data del Check-out </label>
        <input id="dataOut" type="date" name="dataIn">${prenotazione.dataOut}<br>
        <label for="numOspiti">Numero di Ospiti </label>
        <input id="numOspiti" type="number" name="numOspiti">${prenotazione.numOspiti}<br>
        <input type="submit" value="Modifica Prenotazione">
    </form>
</div>
</body>
</html>