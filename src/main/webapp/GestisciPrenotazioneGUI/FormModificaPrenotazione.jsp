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
        <input type="hidden" id="idPrenotazione" name="idPrenotazione" value="${id_prenotazione}">
        <label for="dataIn">Data del Check-in </label>
        <input id="dataIn" type="date" name="dataIn"><br>
        <label for="dataOut">Data del Check-out </label>
        <input id="dataOut" type="date" name="dataIn"><br>
        <label for="numOspiti">Numero di Ospiti </label>
        <input id="numOspiti" type="number" name="numOspiti"><br>
        <input type="submit" value="Modifica Prenotazione">
    </form>
</div>
</body>
</html>