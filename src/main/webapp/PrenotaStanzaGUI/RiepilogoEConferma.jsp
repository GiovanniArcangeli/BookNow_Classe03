<!DOCTYPE html>
<html>
<head>
    <title>Riepilogo Prenotazione</title>
    <link rel="stylesheet" type="text/css" href="RiepilogoEConferma.css">
</head>
<body>
<p>Riepilogo prenotazione</p>
<div id="dati">
    <p>Data Check-in: ${prenotazione.dataIn}</p>
    <p>Data Check-out: ${prenotazione.dataOut}</p>
    <p>Numero Ospiti: ${prenotazione.numOspiti}</p>
    <p>Nome Struttura: ${struttura.nome}</p>
    <p>Numero Stanza: ${numeroStanza}</p>
    <button onclick="location.href='prenota/conferma?id=${struttura.ID_Struttura}&num=${numeroStanza}'">Conferma</button>
</div>
</body>
</html>
