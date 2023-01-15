<!DOCTYPE html>
<html>
<head>
    <title>Modifica Prenotazione</title>
    <link rel="stylesheet" type="text/css" href="./FormModificaPrenotazione.css">
    <script src="../jquery.js"></script>
    <script src="./FormModificaPrenotazione.js"></script>
</head>
<body>
<p>Modifica i dati della prenotazione</p>
<div id="form">
    <form id="prenotazioneForm" method="post" action="../gestisci-prenotazione">
        <input type="hidden" id="id" name="id" >

        <label for="dataIn">Data del Check-in </label>
        <input id="dataIn" type="text" name="dataIn" ><br>

        <label for="dataOut">Data del Check-out </label>
        <input id="dataOut" type="text" name="dataOut" ><br>

        <label for="numOspiti">Numero di Ospiti </label>
        <input id="numOspiti" type="text" name="numOspiti"><br>

        <input type="button" value="Modifica Prenotazione" onclick="FormModificaPrenotazione()">
    </form>
</div>
</body>
</html>