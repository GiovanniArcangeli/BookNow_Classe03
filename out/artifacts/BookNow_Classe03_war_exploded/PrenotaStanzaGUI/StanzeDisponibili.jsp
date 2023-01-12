<!DOCTYPE html>
<html>
<head>
    <title>Stanze Disponibili</title>
    <link rel="stylesheet" type="text/css" href="StanzeDisponibili.css">
</head>
<body>
<h2 id="stanzeDisponibili">Stanze disponibili</h2>
    <!-- Lista Stanze con Stanza.struttura = struttura && Stanza.isDisponibile(dataIn, dataOut) && Stanza.capienza > numOspiti -->
    <table class="stanze">
        <tr>
            <td>
                <p>Stanza.numero</p>
                <p>Stanza.costo</p>
                <p>Stanza.Capienza</p>
                <p>Stanza.Descrizione</p>
                <p>Stanza.Servizi</p>
            </td>
            <td>
                <button>Prenota</button>
            </td>
        </tr>
    </table>
</body>
</html>
